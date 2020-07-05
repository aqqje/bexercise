package com.aqqje.springbootdemo;

import com.aqqje.springbootdemo.dm04.EnableConfiguration;
import com.aqqje.starter.AqqjeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.aqqje.springbootdemo.dm03.MybatisConfiguration;
import com.aqqje.springbootdemo.dm02.RedisConfiguration;

// @EnableConfiguration
@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(SpringBootDemoApplication.class, args);
        System.out.println(run.getBean(MybatisConfiguration.class));
        System.out.println(run.getBean(RedisConfiguration.class));
        System.out.println(run.getBean(AqqjeService.class));
        System.out.println(run.getBean(CustomerHealthIndicator.class));
    }

}
