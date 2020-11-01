package spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @author AqqJE
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPRequestMapping {
    String value() default "";
}

