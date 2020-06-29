package com.aqqje.springbootdemo.dm01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Conditional(AqCondition.class)
    @Bean
    public Dm01Service dm01Service(){
        return new Dm01Service();
    }
}
