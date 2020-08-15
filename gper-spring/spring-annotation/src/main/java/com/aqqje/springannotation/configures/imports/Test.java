package com.aqqje.springannotation.configures.imports;


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
        System.out.println("IoC 容器化");

        // 通过FactoryBean 注入的值
        System.out.println(app.getBean("monkey").getClass());
        Object monkey1 = app.getBean("monkey");
        Object monkey2 = app.getBean("monkey");

        System.out.println("是否单例：" + monkey1 == monkey2);
        // 拿到构建monkey的FactoryBean
        Object beanFactoryBean = app.getBean("&monkey");
        System.out.println(beanFactoryBean);

        String[] beanDefinitionNames = app.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames)
                .replaceAll("\\[|\\]", "")
                .replaceAll(",", "\n"));
    }
}
