package com.aqqje.demo.dm02;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Dm02Main {
    public static void main(String[] args) {

        /*混合使用， 注解 + xml*/
        FileSystemXmlApplicationContext fsxac = new FileSystemXmlApplicationContext("classpath:aplicationContext.xml");
        Object demo2Service = fsxac.getBean("demo2Service");
        System.out.println(demo2Service);
    }
}
