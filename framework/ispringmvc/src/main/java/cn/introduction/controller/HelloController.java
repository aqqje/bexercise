package cn.introduction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    /**
     *
     * @return
     */
    @RequestMapping("/sayhello.do")
    public String hello(){
        return "success";
    }

    @RequestMapping("/index.do")
    public String index(){
        return "/para.jsp";
    }
}
