package cn.introduction.controller;

import cn.introduction.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/param")
public class ParaController {

    @RequestMapping("/hrefpara.do")
    public String hrefpara(String username, String password){
        System.out.println("username:"+username+",password:"+password);
        return "success";
    }
    @RequestMapping(value = "/formtpara.do"/*, method = RequestMethod.POST*/)
    public String formtpara(Account account){
        System.out.println(account);
        return "success";
    }

    @RequestMapping(value = "/formtparaset.do"/*, method = RequestMethod.POST*/)
    public String formtparaset(Account account){
        System.out.println(account);
        return "success";
    }

}
