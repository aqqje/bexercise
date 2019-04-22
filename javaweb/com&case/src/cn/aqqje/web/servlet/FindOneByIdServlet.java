package cn.aqqje.web.servlet;

import cn.aqqje.domain.User;
import cn.aqqje.service.UserService;
import cn.aqqje.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findOneByIdServlet")
public class FindOneByIdServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = service.findOneById(id);
        if(user != null){
            request.setAttribute("updateUser", user);
            request.getRequestDispatcher("updateUser.jsp").forward(request,response);
        }else{
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("修改用户失败!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
