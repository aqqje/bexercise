package com.aqqje.springannotation.configures.lazy;

import com.aqqje.springannotation.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
@Configuration
public class MyConfig {
    /**
     * 表示延迟初始化，在使用的时候再初始化
     * @return
     */
    @Lazy
    @Bean
    public Person person(){
        System.out.println("Lazy------------");
        return new Person();
    }
}
