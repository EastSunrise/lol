package wsg.lol.service.aop;

import wsg.lol.common.enums.system.RegionEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom aspect for real-time update of data.
 * <p>
 * Return value of the method will be ignored.
 *
 * @author Kingen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RealScheduled {

    /**
     * Regions assigned.
     */
    RegionEnum[] regions() default {};
}
