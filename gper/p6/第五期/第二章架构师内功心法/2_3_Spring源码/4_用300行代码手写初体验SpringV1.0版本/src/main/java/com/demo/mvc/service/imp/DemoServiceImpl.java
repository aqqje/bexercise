package com.demo.mvc.service.imp;

import com.demo.mvc.service.DemoService;


public class DemoServiceImpl implements DemoService {
    @Override
    public String say(String name) {
        return "My name is " + name;
    }
}
