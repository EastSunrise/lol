package wsg.lol.common.annotation;

import org.springframework.expression.Expression;
import wsg.lol.common.enums.system.RegionEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicate to invoke the method under the assigned api.
 * <p>
 * If both {@link #region()} and {@link #regionString()} are assigned, {@link #region()} which is not {@link RegionEnum#LOL} will be used.
 *
 * @author Kingen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AssignRegion {

    /**
     * The region assigned.
     */
    RegionEnum region() default RegionEnum.LOL;

    /**
     * String of {@link Expression} for the region.
     */
    String regionString() default "";
}
