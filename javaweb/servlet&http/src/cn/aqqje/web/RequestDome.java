package cn.aqqje.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RequestDome")
public class RequestDome extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /*1. 获取请求方式 ：GET
        String getMethod()
        2. (*)获取虚拟目录：/servlet
        String getContextPath()
        3. 获取Servlet路径: /servlet
        String getServletPath()
        4. 获取get方式请求参数：name=zhangsan
        String getQueryString()
        5. (*)获取请求URI：/aqqje/demo
        String getRequestURI():		/aqqje/servlet
        StringBuffer getRequestURL()  :http://localhost/aqqje/dome

        URL:统一资源定位符 ： http://localhost/aqqje/demo	中华人民共和国
        URI：统一资源标识符 : /aqqje/demo					共和国

        6. 获取协议及版本：HTTP/1.1
        String getProtocol()

        7. 获取客户机的IP地址：
        String getRemoteAddr()*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  1. 获取请求方式 ：GET
        String method = request.getMethod();
        System.out.println(method);
        //  2. (*)获取虚拟目录：/servlet
        String contextPath = request.getContextPath();
        System.out.println(contextPath);
        //  3. 获取Servlet路径: /servlet
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        //  4. 获取get方式请求参数：name=zhangsan
        String queryString = request.getQueryString();
        System.out.println(queryString);
        //  5. (*)获取请求URI：/aqqje/demo
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        //  StringBuffer getRequestURL()  :http://localhost/aqqje/dome
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);
        //  6. 获取协议及版本：HTTP/1.1
        String protocol = request.getProtocol();
        System.out.println(protocol);
        // 7. 获取客户机的IP地址：
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);

        String header = request.getHeader("user-agent");
        if(header.contains("Chrome")){
            System.out.println("chorme");
        }
    }
}
