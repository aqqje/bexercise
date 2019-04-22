package cn.aqqje.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RequestDome5")
public class RequestDome5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
        request.setCharacterEncoding("utf-8");
        Object aqqje = request.getAttribute("aqqje");
        System.out.println(aqqje);
        System.out.println("RequestDome5===============");
    }
}
