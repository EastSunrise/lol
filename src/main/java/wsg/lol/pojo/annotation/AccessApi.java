package wsg.lol.pojo.annotation;

import java.lang.annotation.*;

/**
 * wsg
 *
 * @author wangsigen
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessApi {
    int value() default 1000;
}
