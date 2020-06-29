package com.aqqje.demo.dm06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TaskMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TaskConfiguration.class);

    }
}
