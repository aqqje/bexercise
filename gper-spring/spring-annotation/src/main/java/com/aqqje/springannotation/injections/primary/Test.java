package com.aqqje.springannotation.injections.primary;


import com.aqqje.springannotation.project.Server.MyService;
import com.aqqje.springannotation.project.dao.MyDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 *
 *
 **/
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        MyService myService = (MyService) app.getBean("myService");
        System.out.println(myService.test().toString());
        MyDao myDao = (MyDao) app.getBean("myDao");
        System.out.println(myDao.toString());
    }
}
