package com.aqqje.demo.dm01;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class DmMain {

    public static void main(String[] args) {

        /*通过解析配置上下文获取bean  xml 使用*/
        FileSystemXmlApplicationContext fsxac = new FileSystemXmlApplicationContext("classpath:aplicationContext.xml");
        Object hello = fsxac.getBean("hello");
        System.out.println(hello);
    }
}
