package cn.aqqje.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/responseDome3")
public class ResponseDomeServlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置字符流字符集, 默认为 ISO-8859-1, 但不对浏览器字符集进行设置
//        response.setCharacterEncoding("utf-8");

        // 设置响应头信息
//        response.setHeader("content-type", "text/html;charset=utf-8");

        response.setContentType("text/html;charset=utf-8");
        // 获取字符输出流, 并输出数据
        response.getWriter().write("你好啊, response");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
