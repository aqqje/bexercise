package com.aqqje.springannotation.configures.imports;

import com.aqqje.springannotation.project.entity.Cat;
import com.aqqje.springannotation.project.entity.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 *
 * @Import 导入外部资源
 **/
@Configuration
@Import(value = {Cat.class, MyImportBeanDefinitionRegistrar.class, MyImportSeletector.class})
public class MyConfig {
    // 给IoC中注册Bean的方式
    // 1,@Bean 直接导入单个类
    // 2，@ComponentScan 包扫描默认是扫描（@Controller, @Service, @Repository, @Component）
    // 3, @Import 快速给容器导入组件Bean
    //      a,@Import 直接传参导入
    //      b, ImportSeletector 自定义导入规则
    //      c, ImportBeanDefinitionRegistrar,实现ImportBeanDefinitionRegistrar接口 可以手动注入到IoC容器中
    // 4, FactoryBean 把需要注入的对象封装为FactoryBean
    //      a, FactoryBean 负责将Bean注入到容器的Bean
    //      b，BeanFactory 从IoC中获得Bean对象

    @Bean
    public Person person(){
        return new Person();
    }

    @Bean
    public MyFactoryBean monkey(){
        return new MyFactoryBean();
    }
}
