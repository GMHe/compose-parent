package cn.compose.admin.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InterfaceAutoIdempotent {
    //单位秒(S)
    int lockTime() default 10;
}
