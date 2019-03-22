package wsg.lol.common.annotation;

import java.lang.annotation.*;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-08 16:29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessApi {
    int value() default 1000;
}
