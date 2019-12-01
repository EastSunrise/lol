package wsg.lol.common.annotation;

import wsg.lol.common.enums.system.PlatformRoutingEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicate the datasource to be used.
 *
 * @author Kingen
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Datasource {

    PlatformRoutingEnum platform() default PlatformRoutingEnum.LOL;
}
