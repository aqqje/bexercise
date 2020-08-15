package com.aqqje.springannotation.injections.primary;

import com.aqqje.springannotation.project.dao.MyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 *
 **/
@Configuration
@ComponentScan(value = {"com.aqqje.springannotation.project"})
public class MyConfig {

    /**
     * 自动装配时当出现多个相名的bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
     * @return
     */
    @Bean("dao")
    public MyDao dao(){
        MyDao dao = new MyDao();
        dao.setFlag(5);
        return dao;
    }



    @Primary
    @Bean("dao")
    public MyDao dao1(){
        MyDao dao = new MyDao();
        dao.setFlag(7);
        return dao;
    }
}
