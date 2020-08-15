package com.aqqje.springannotation.configures.Configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 *
 * @Configuration 把一个类当作为一个IOC容器，它的某个方法如果注册了@Bean,就会作为这个Spring容器中的Bean;
 **/
@Configuration
public class MyConfig {
}
