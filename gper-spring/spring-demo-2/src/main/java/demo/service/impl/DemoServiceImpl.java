package demo.service.impl;

import demo.service.DemoService;
import spring.framework.annotation.GPService;

@GPService
public class DemoServiceImpl implements DemoService {

    public String demo(String name) {
        return "My name is " + name;
    }
}
