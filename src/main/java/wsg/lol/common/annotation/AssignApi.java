package wsg.lol.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicate to invoke the method under the assigned api.
 *
 * @author Kingen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AssignApi {

    /**
     * The username of the api used to encrypt the data.
     */
    String encryptUsername() default "";
}
