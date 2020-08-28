package com.aqqje.v2.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @Author AqqJe
 * @Date 2020/8/15
 * @Version 1.0
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AQRequestParam {
    String value() default "";
}
