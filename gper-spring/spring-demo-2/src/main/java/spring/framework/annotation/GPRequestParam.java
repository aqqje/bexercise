package spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @author AqqJE
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPRequestParam {

    String value() default "";

    boolean required() default true;

}
