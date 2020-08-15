package com.aqqje.springannotation.configures.scope;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

        Object bean1 = app.getBean("person");
        System.out.println(bean1);
        Object bean2 = app.getBean("person");
        System.out.println(bean1 == bean2);
    }
}
