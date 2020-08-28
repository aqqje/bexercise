package com.aqqje.v1.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @Author AqqJe
 * @Date 2020/8/15
 * @Version 1.0
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AQAutowired {
    String value() default "";
}
