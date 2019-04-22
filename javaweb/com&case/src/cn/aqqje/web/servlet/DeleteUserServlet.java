package cn.aqqje.web.servlet;

import cn.aqqje.service.UserService;
import cn.aqqje.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*设置字符集*/
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /*获取id*/
        int id = Integer.parseInt(request.getParameter("id"));
        /*删除操作*/
        service.deleteUserById(id);
        /*转发至UserListServlet*/
        request.getRequestDispatcher("userListServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
