package com.aqqje.springannotation.project.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
public class Car {

    //@PostConstruct
    public void addOil(){
        System.out.println("加油");
    }

    public void run(){
        System.out.println("运行");
    }

    // @PreDestroy
    public void close(){
        System.out.println("熄火");
    }
}
