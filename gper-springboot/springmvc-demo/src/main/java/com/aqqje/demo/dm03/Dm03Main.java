package com.aqqje.demo.dm03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Dm03Main {

    public static void main(String[] args) {
        /*可以去xml化使用 Configuration 注解*/
        AnnotationConfigApplicationContext acpc =
                new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Dm03Service bean = acpc.getBean(Dm03Service.class);
        System.out.println(bean);
    }
}
