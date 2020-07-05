package com.aqqje.springbootdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author AqqJe
 * @Date 2020/7/3
 * @Version 1.0
 **/
@Configuration
public class EndpointConfiguration {

    @Bean
    public CustomerHealthIndicator customerHealthIndicator(){
        return new CustomerHealthIndicator();
    }
}
