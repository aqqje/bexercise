package spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @author AqqJE
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPService {
    String value() default "";
}
