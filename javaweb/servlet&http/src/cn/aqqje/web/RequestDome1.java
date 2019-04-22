package cn.aqqje.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RequestDome1")
public class RequestDome1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String referer = request.getHeader("referer");
        //System.out.println(referer);// http://localhost:8080/dome/
        if(referer.contains("/servlet")){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("播放中");
        }else{
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("想看吗,来优酷吧");
        }
    }
}
