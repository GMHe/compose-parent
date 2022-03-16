package cn.compose.admin.annotations.lockConfig;

import cn.compose.admin.annotations.InterfaceAutoIdempotent;
import cn.compose.admin.base.ApiCodes;
import cn.compose.admin.base.Response;
import cn.compose.admin.exception.RateLimiterException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 接口幂等性的 -- 分布式锁实现
 */
@Slf4j
@Aspect
@Component
public class ReqSubmitAspect {

    @Autowired
    private RedisLock redisLock;

    @Pointcut("@annotation(cn.compose.admin.annotations.InterfaceAutoIdempotent)")
    public void idePointCut() {
    }

    @Around("idePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 使用分布式锁 机制-实现
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        InterfaceAutoIdempotent ide = method.getAnnotation(InterfaceAutoIdempotent.class);
        int lockSeconds = ide.lockTime();

        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        Assert.notNull(request, "request can not null");

        // 获取请求的凭证，本项目中使用的JWT,可对应修改
        String token = request.getHeader("Authorization");
        String requestURI = request.getRequestURI();

        String key = getIdeKey(token, requestURI);
        String clientId = UUID.randomUUID().toString();

        // 获取锁
        boolean lock = redisLock.tryLock(key, clientId, lockSeconds);
        log.info("lock {} tryLock key = [{}], clientId = [{}]", lock, key, clientId);

        if (lock) {
            log.info("tryLock success, key = [{}], clientId = [{}]", key, clientId);
            // 获取锁成功
            Object result;
            try {
                // 执行进程
                result = joinPoint.proceed();
            } finally {
                // 解锁
                Boolean isRel = redisLock.releaseLock(key, clientId);
                if (!isRel) {
                    throw new RuntimeException("解锁失败，锁key为:" + key);
                }
                log.info("releaseLock success, key = [{}], clientId = [{}]", key, clientId);
            }
            return result;
        } else {
            // 获取锁失败，认为是重复提交的请求
            log.info("访问过于频繁，请稍后再试！, key = [{}]", key);
            //向上抛出异常，在全局捕获异常做处理
            throw new RateLimiterException("访问过于频繁，请稍后再试!");
        }
    }

    private String getIdeKey(String token, String requestURI) {
        return token + requestURI;
    }
}