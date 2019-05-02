package cn.introduction.controller;

import cn.introduction.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/anno")
@SessionAttributes(value = {"username", "password"})
public class AnnoController {

    @RequestMapping("/putSession.do")
    public String putSession(Model model){
        model.addAttribute("username", "aqqje");
        model.addAttribute("password", "1234560");
        System.out.println("putSession: 增加了session");
        return "success";
    }

    @RequestMapping("/getSession.do")
    public String getSession(ModelMap model){
        System.out.println("获取了session"+model.get("username")+model.get("password"));
        return "success";
    }

    @RequestMapping("/removeSession.do")
    public String removeSession(SessionStatus status){
        status.setComplete();
        return "success";
    }

    /**
     * @RequestParam 用于指定请求连接中的参数并绑定到该注解后紧跟的形参
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping("/requestParam.do")
    public String requestParam(@RequestParam(name="username") String name, @RequestParam(name="password") String pwd){
        System.out.println("username:"+name+",password:" +pwd);
        return "success";
    }

    /**
     * 用于将请求中的数据接连为一个序列化一个字符串并绑定到该注解后紧跟的形参中
     * @param body
     * @return
     */
    @RequestMapping(value = "/requestBody.do", method = RequestMethod.POST)
    public String requestBody(@RequestBody String body){
        System.out.println(body);
        return "success";
    }

    /**
     * 用于 restful 风格的请求连接, 绑定一个 url 中的一个占位符
     * @param id
     * @return
     */
    @RequestMapping(value = "/pathVariable/{sid}.do", method = RequestMethod.GET)
    public String pathVariable(@PathVariable("sid") int id){
        System.out.println(id);
        return "success";
    }

    @RequestMapping(value = "/requestHeader.do")
    public String requestHeader(@RequestHeader("Accept") String  accept){
        System.out.println(accept);
        return "success";
    }

    /**
     * @CookieValue 获取指定的 cookie
     * @param cookie
     * @return
     */
    @RequestMapping(value = "/cookieValue.do")
    public String cookieValue(@CookieValue(value = "JSESSIONID",required = false) String cookie){
        System.out.println(cookie);
        return "success";
    }
    /*==================================一个实例======================================================*/
    /**
     * 获取要修改的用户
     * @param user
     * @param map
     */
    @ModelAttribute
    public void getUser(User user,Map<String,User> map){
        user = findById(user.getName());
        System.out.println("getUser"+ user);
        map.put("user", user);
    }

    /**
     * 跳转到修改页面
     * @param user
     * @return
     */
    @RequestMapping("/toUpatePage.do")
    public ModelAndView toUpatePage(@ModelAttribute("user") User user){
        ModelAndView mv = new ModelAndView();
        System.out.println("toUpatePage:" + user);
        mv.addObject("user", user);
        mv.setViewName("update");
        return mv;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public String update(@ModelAttribute("user")User user){
        System.out.println("修改后的user:"+user);
        save(user);
        return "success";
    }
    /*==================================以下模拟业务层====================================================*/

    public User findById(String name){
        User user = new User();
        user.setName(name);
        user.setDate(new Date());
        user.setAge(12);
        return  user;
    }

    public void save(User user){
        System.out.println(user);
    }
    /*===============================================================================================*/
}
