package com.aqqje.springannotation.configures.scope;

import com.aqqje.springannotation.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
@Configuration
public class MyConfig {
    /**
     * 用于指定scope作用域（用在类上）
     * prototype 多例
     * singleton 单例 （默认）
     * request 主要应用于web模块，同一次请求只创建一个实例
     * session 主要应用于web模块，同一次session只创建一个实例
     * @return
     */
    @Scope(value = "prototype")
    @Bean
    public Person person(){
        return new Person();
    }
}
