package demo.controller;

import demo.service.DemoService;
import spring.framework.annotation.GPAutowired;
import spring.framework.annotation.GPController;
import spring.framework.annotation.GPRequestMapping;
import spring.framework.annotation.GPRequestParam;

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
