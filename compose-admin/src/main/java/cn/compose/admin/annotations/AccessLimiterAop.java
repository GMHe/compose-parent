package cn.compose.admin.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimiterAop {
    int limit();

    String methodKey() default "";
}