package cn.aqqje.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        //获取相关数据
        String cc_code =(String)session.getAttribute("checkCode");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        //移除验证码
        session.removeAttribute("checkCode");
        //一.判断验证码
        if(checkCode!=null && checkCode.equalsIgnoreCase(cc_code)){//A.true
            //二.判断用户与密码
            if(name.equals("aqqje") && password.equals("12345")){
                //a.true
                session.setAttribute("name", "aqqje");
                response.sendRedirect(request.getContextPath()+"/success.jsp");
            }else{
                //b.false
                request.setAttribute("infoError","name||password Error");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }else{//B.false
            request.setAttribute("codeError","codeError");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
