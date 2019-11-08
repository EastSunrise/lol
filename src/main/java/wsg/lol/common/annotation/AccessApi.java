package wsg.lol.common.annotation;

import java.lang.annotation.*;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessApi {
    int value() default 1000;
}