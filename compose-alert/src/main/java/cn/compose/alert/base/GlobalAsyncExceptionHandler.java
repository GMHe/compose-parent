package cn.compose.alert.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName GlobalAsyncExceptionHandler
 * @Description 类描述
 * @Author hgm
 * @Date 2021/11/15 11:23
 * @Version 1.0
 **/
@Component
@Slf4j
public class GlobalAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("[handleUncaughtException][method({}) params({}) 发生异常]",
                method, params, ex);
    }

}
