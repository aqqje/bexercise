package spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @author AqqJE
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPAutowired {
    String value() default "";
}
