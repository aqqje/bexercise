package com.aqqje.springannotation.configures.conditional;

import com.aqqje.springannotation.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
@Configuration
public class MyConfig {

    /**
     * @Conditional Spring4 开始提供， 它的作用是按照一定的条件进行判断，满足条件给容器注册Bean
     * @return
     */
    @Conditional(WindowsConditional.class)
    @Bean
    public Person aqqje(){
        return new Person("aqqje", 20);
    }

    @Conditional(WindowsConditional.class)
    @Bean
    public Person yjgm(){
        return new Person("aqqje", 20);
    }

    @Conditional(LinuxConditional.class)
    @Bean
    public Person liudd(){
        return new Person("aqqje", 20);
    }
}
