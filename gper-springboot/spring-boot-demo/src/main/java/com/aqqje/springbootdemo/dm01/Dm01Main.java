package com.aqqje.springbootdemo.dm01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Dm01Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        Dm01Service bean = a.getBean(Dm01Service.class);
        System.out.println(bean);
    }
}
