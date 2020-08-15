package com.aqqje.springannotation.project.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
@Repository("myDao")
public class MyDao {

    private int flag = 1;

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "MyDao{" +
                "flag=" + flag +
                '}';
    }
}
