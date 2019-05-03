package cn.viewtype.controller;

import cn.viewtype.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应数据和结果视图
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 字符串
     * @return
     */
    @RequestMapping("/string.do")
    public String string(){
        System.out.println("string............");
        return "success";
    }

    /**
     * modelAndView
     * @return
     */
    @RequestMapping("/modelAndView.do")
    public ModelAndView modelAndView(){
        ModelAndView mv = new ModelAndView();
        System.out.println("string............");
        User modelAndView = new User();
        modelAndView.setName("modelAndView");
        modelAndView.setAge(20);
        modelAndView.setGender("男");
        mv.addObject("modelAndView", modelAndView);
        mv.setViewName("success");
        return mv;
    }

    @RequestMapping("/idvoid.do")
    public void idvoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*User idvoid = new User();
        idvoid.setName("idvoid");
        idvoid.setGender("女");
        idvoid.setAge(18);
        request.setAttribute("idvoid", idvoid);*/
        // 转发请求
        /*request.getRequestDispatcher("/pages/success.jsp").forward(request, response);*/
        // 重定向
        User idvoid = new User();
        idvoid.setName("idvoid");
        idvoid.setGender("女");
        idvoid.setAge(18);
        request.getSession().setAttribute("idvoid", idvoid);
        response.sendRedirect("string.do");
        // 设置中文乱码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // response 直接响应结果
        /*response.getWriter().write(idvoid.toString());*/
    }

    /**
     * forword or redirect 关键字页面跳转
     */
    @RequestMapping("/forwardOrRedirect")
    public String forwardOrRedirect(){
        System.out.println("forwardOrRedirect..........");
        // 转发
        return "forward:/pages/success.jsp";
        // 重定向
        //return "redirect:/pages/success.jsp";
    }

    /**
     * @ResponseBody 与 @RequestBody + jackson 实现 json
     */
    @RequestMapping(value = "/requestBody.do", method = RequestMethod.POST)
    public @ResponseBody User requestBody(@RequestBody User user){
        user.setAge(100);
        System.out.println("User修改后:"+user);
        return user;
    }
}
