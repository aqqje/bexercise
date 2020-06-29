package com.aqqje.demo.dm05;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TaskMain {

    public static void main(String[] args) {
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("classpath:aplicationContext.xml");

    }
}
