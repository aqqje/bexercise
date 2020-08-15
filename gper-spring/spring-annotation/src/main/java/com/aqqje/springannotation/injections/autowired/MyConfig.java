package com.aqqje.springannotation.injections.autowired;

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



    @Bean("dao")
    public MyDao dao1(){
        MyDao dao = new MyDao();
        dao.setFlag(2);
        return dao;
    }
}
