package com.aqqje.springannotation.configures.Configuration;


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
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        String[] beanNames = app.getBeanDefinitionNames();
        // \\[|\\]是两次转义， javac编译时将\\|转义为\|,传给匹配器（解析这个正则表达式），解析时\|相当于|。这个两个语言，两层转换
        System.out.println(Arrays.toString(beanNames).replaceAll("\\[|\\]", "").replaceAll(",", "\n"));
    }
}
