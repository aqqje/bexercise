package com.aqqje.springannotation.configures.lifecyle;


import com.aqqje.springannotation.project.entity.Car;
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
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(com.aqqje.springannotation.configures.lifecyle.MyConfig.class);
        Car car = (Car) app.getBean("car");
        car.run();
        app.close();
    }
}
