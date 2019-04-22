package cn.aqqje.web.servlet;

import cn.aqqje.domain.BeanPage;
import cn.aqqje.service.UserService;
import cn.aqqje.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/beanPageServlet")
public class BeanPageServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        Map<String, String[]> condation = request.getParameterMap();
        request.setAttribute("condation", condation);
        if(currentPage == null || currentPage == "" ){
            currentPage = "1";
        }
        if(rows == null || rows == "" ){
            rows = "5";
        }
        BeanPage pb = service.beanPage(Integer.parseInt(currentPage), Integer.parseInt(rows), condation);
        request.setAttribute("pb", pb);
        request.getRequestDispatcher("/user_list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
