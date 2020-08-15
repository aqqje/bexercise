package com.aqqje.springannotation.project.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
public class Train implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("销毁");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化");
    }
}
