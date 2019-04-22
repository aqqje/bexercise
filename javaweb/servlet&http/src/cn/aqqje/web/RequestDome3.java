package cn.aqqje.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/RequestDome3")
public class RequestDome3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
        request.setCharacterEncoding("utf-8");
        /*1. String getParameter(String name):根据参数名称获取参数值    username=zs&password=123
        2. String[] getParameterValues(String name):根据参数名称获取参数值的数组  hobby=xx&hobby=game
        3. Enumeration<String> getParameterNames():获取所有请求的参数名称
        4. Map<String,String[]> getParameterMap():获取所有参数的map集合*/
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            System.out.println(parameterNames.nextElement());
        }
        System.out.println("================");
        String[] pwds = request.getParameterValues("pwd");
        for(String item: pwds){
            System.out.println(item);
        }
        System.out.println("================");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> strings = parameterMap.keySet();
        for(String key: strings){
            String[] values = parameterMap.get(key);
            for(String value: values){
                System.out.println(value);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
