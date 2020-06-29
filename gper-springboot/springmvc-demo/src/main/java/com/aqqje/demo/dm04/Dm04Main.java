package com.aqqje.demo.dm04;

import com.aqqje.demo.dm03.Dm03Service;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.aqqje.demo.dm03.SpringConfiguration;
public class Dm04Main {

    public static void main(String[] args) {
        /*使用import注解引入其他配置类*/

        AnnotationConfigApplicationContext acac =
                new AnnotationConfigApplicationContext(ImportSelecterConfiguration.class);
        System.out.println(acac.getBean(ImportSelecterService.class));
        System.out.println(acac.getBean(Dm03Service.class));
    }
}
