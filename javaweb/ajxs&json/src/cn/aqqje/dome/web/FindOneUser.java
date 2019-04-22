package cn.aqqje.dome.web;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/findOneUser")
public class FindOneUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        HashMap<String, Object> map = new HashMap<>();
        if("tom".equals(username)){
            map.put("msg", "用户名可用");
            map.put("userExsit", true);
        }else{
            map.put("userExsit", false);
            map.put("msg", "用户名不可用");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMap = objectMapper.writeValueAsString(map);
        response.getWriter().write(jsonMap);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
