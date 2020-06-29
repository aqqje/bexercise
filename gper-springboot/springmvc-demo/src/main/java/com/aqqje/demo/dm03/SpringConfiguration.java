package com.aqqje.demo.dm03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @ComponentScan("com.aqqje.demo.dm03")
@Configuration
public class SpringConfiguration {

    @Bean
    public Dm03Service dm03Service(){
        return new Dm03Service();
    }
}
