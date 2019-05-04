package cn.interceptor.controller;

import cn.interceptor.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 拦截器测试
     * @return
     */
    @RequestMapping("/interceptor.do")
    public String interceptor(){
        return "success";
    }

    /**
     * 测试拦截用户请求
     * @return
     */
    @RequestMapping("/main.do")
    public String main(){
         return "main";
    }

    /**
     * 跳转至登录页
     * @return
     */
    @RequestMapping("/tologin.do")
    public String tologin(){
        return "login";
    }

    /**
     * 登录请求处理
     * @return
     */
    @RequestMapping("/login.do")
    public String login(HttpServletRequest request, String name, String pwd){
        if(name == null || pwd == null){
            return "error";
        }
        User loginUser = findByName(name);
        if(loginUser.getPassword().equals(pwd)){
            request.getSession().setAttribute("loginUser", loginUser);
            return "success";
        }else{
            return "error";
        }
    }
    /**
     * 模拟业务层
     * @param name
     * @return
     */
    public User findByName(String name){
        User user = new User(name, "123");
        return user;
    }
}
