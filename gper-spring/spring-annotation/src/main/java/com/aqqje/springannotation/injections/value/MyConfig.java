package com.aqqje.springannotation.injections.value;

import com.aqqje.springannotation.project.entity.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 *
 * @Value 普通数据类型赋值
 **/
@Configuration
@PropertySource("classpath:value.properties")
public class MyConfig {

    /**
     * @Value 表示
     * 1，支持基础数据类型
     * 2，spring El 表达式
     */
    @Bean
    public Bird bird(){
        return new Bird();
    }
}
