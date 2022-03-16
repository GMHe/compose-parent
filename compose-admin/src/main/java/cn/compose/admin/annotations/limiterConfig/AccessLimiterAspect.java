package cn.compose.admin.annotations.limiterConfig;

import cn.compose.admin.annotations.AccessLimiterAop;
import cn.compose.admin.biz.AccessLimiterBiz;
import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;


@Slf4j
@Aspect
@Component
public class AccessLimiterAspect {
    @Autowired
    private AccessLimiterBiz accessLimiter;

    //根据注解的位置，自己修改
    @Pointcut("@annotation(cn.compose.admin.annotations.AccessLimiterAop)")
    public void cut() {
        log.info("cut");
    }

    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        //获取方法签名，作为methodkey
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AccessLimiterAop annotation = method.getAnnotation(AccessLimiterAop.class);

        if (annotation == null) {
            return;
        }
        String key = annotation.methodKey();
        Integer limit = annotation.limit();
        //如果没有设置methodKey，就自动添加一个
        if (StringUtils.isEmpty(key)) {
            Class[] type = method.getParameterTypes();
            key = method.getName();
            if (type != null) {
                String paramTypes = Arrays.stream(type)
                        .map(Class::getName)
                        .collect(Collectors.joining(","));
                key += "#" + paramTypes;
            }

        }
        //调用redis
        accessLimiter.limitAccess(key, limit);
    }
}