package com.aqqje.spring.demo.service.impl;

import com.aqqje.spring.annotation.GPService;
import com.aqqje.spring.demo.service.DemoService;

@GPService
public class DemoServiceImpl implements DemoService {

    public String demo(String name) {
        return "My name is " + name;
    }
}
