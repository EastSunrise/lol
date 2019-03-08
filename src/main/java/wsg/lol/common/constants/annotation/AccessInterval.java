package wsg.lol.common.constants.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-08 16:29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessInterval {
    int value() default 1000;
}
