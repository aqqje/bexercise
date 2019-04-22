package cn.aqqje.servlet;

import com.sun.org.apache.bcel.internal.classfile.Code;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 判断一个服务器是否有一个cookie叫lastTime
 *  1.没有: 首次访问,输出,您好!,欢迎首次访问
 *  2. 有: 再次访问,输出,您好!,您上次的访问时间为 2019年04月2日 14:45:55
 */
@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 0. 设置responset响应编码
        response.setContentType("text/html;charset=utf-8");
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日 HH:mm:ss");
        // 1.获取cookie数据cookies
        Cookie[] cookies = request.getCookies();
        // 1.1遍历cookies并判断是否存在一个叫lastTime的cookie
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            // 2.存在lastTime
            if("lastTime".equals(cookie.getName())){
                flag = true;
                // 2.1设置新的lastTime并设置储存时间
                Date date = new Date();
                String str_date = sdf.format(date);
                str_date = URLEncoder.encode(str_date, "utf-8");
                cookie.setValue(str_date);
                cookie.setMaxAge(60 * 60 * 24);
                // 2.2获取lastTime的值
                String value = cookie.getValue();
                value = URLDecoder.decode(value, "utf-8");
                // 2.3输出:您好!,您上次的访问时间为 2019年04月2日 14:45:55
                response.getWriter().write("<h1>您好!,您上次的访问时间为"+ value +"</h1>");
                break;
             }
        }
        // 3.不存在lastTime
        if((cookies == null || cookies.length == 0 || false == flag)){
            // 3.1设置新的lastTime并设置储存时间
            Date date = new Date();
            String str_date = sdf.format(date);
            str_date = URLEncoder.encode(str_date, "utf-8");
            Cookie cookie = new Cookie("lastTime", str_date);
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);
            // 3.2输出:您好!,欢迎首次访问
            response.getWriter().write("<h1>您好!,欢迎首次访问</h1>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
