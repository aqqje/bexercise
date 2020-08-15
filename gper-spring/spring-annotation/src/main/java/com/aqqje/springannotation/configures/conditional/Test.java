package com.aqqje.springannotation.configures.conditional;


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
        System.out.println("IOC 容器初始化");
    }
}
