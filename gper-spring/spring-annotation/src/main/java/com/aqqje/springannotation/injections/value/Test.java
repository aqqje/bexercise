package com.aqqje.springannotation.injections.value;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 *
 *
 **/
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        Object bird = app.getBean("bird");
        System.out.println(bird);

        String color = app.getEnvironment().getProperty("color");
        System.out.println(color);
    }
}
