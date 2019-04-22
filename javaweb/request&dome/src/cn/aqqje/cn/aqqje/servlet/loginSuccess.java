package cn.aqqje.cn.aqqje.servlet;

import cn.aqqje.cn.aqqje.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/loginSuccess")
public class loginSuccess extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User login = (User) request.getAttribute("login");
        /*设置编码*/
        response.setContentType("text/html;charset=utf-8");
        if(login != null){
            response.getWriter().write("登录成功,"+ login.getName() +" 欢迎您!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
