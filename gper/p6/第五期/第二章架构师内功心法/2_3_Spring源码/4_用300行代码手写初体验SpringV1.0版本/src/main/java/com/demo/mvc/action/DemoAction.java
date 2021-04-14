package com.demo.mvc.action;

import com.demo.mvc.service.DemoService;
import com.spring.framework.annotation.GPAutowired;
import com.spring.framework.annotation.GPController;
import com.spring.framework.annotation.GPRequestMapping;

@GPController
@GPRequestMapping("/demo")
public class DemoAction {

    @GPAutowired
    private DemoService demoService;

    @GPRequestMapping("/say")
    public String say(String name){
        return demoService.say(name);
    }
}
