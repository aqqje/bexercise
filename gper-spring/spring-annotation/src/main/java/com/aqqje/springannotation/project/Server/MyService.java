package com.aqqje.springannotation.project.Server;

import com.aqqje.springannotation.project.dao.MyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
@Service
public class MyService {

//    @Qualifier("dao")
//    @Autowired
    @Resource(name = "dao")
    private MyDao myDao;

    public MyDao test(){
        return myDao;
    }
}
