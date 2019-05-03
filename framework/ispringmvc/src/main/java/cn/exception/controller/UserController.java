package cn.exception.controller;

import cn.exception.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/exception.do")
    public String exception() throws CustomException {
        try {
            int i = 1 / 0 ;
        } catch (Exception e) {
            throw new CustomException("算法错误!");
        }

        return "error";
    }
}
