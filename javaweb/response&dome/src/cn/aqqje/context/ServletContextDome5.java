package cn.aqqje.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/contextDome5")
public class ServletContextDome5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 ServletContext 对象
        ServletContext context = this.getServletContext();
       // 分别获取a,b,c三个文件的真实路径
        String pathA = context.getRealPath("/WEB-INF/classes/a.text");// src目录下资源访问
        System.out.println(pathA);

        String pathB = context.getRealPath("/b.text");// web目录下资源访问
        System.out.println(pathB);

        String pathC = context.getRealPath("/WEB-INF/c.text");// WEB-INF目录下资源访问
        System.out.println(pathC);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
