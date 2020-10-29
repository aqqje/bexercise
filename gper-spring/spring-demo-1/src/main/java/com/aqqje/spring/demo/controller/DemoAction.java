package com.aqqje.spring.demo.controller;

import com.aqqje.spring.annotation.GPAutowired;
import com.aqqje.spring.annotation.GPController;
import com.aqqje.spring.annotation.GPRequestMapping;
import com.aqqje.spring.annotation.GPRequestParam;
import com.aqqje.spring.demo.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@GPController
@GPRequestMapping("/demo")
public class DemoAction {

    @GPAutowired
    private DemoService demoService;

    @GPRequestMapping("/name")
    public String Demo(HttpServletRequest req, HttpServletResponse resp, @GPRequestParam("name") String name){
        return demoService.demo(name);
    }
}
