package cn.aqqje.web.servlet;

import cn.aqqje.domain.User;
import cn.aqqje.service.UserService;
import cn.aqqje.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*设置字符集*/
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=utf-8");

        /*获取所有用户信息*/
        List<User> userList = service.findAll();
        if(userList != null || userList.size() > 0){
            session.setAttribute("userList", userList);
            System.out.println(userList);
            response.sendRedirect(request.getContextPath()+"/user_list.jsp");
        }else{
            response.getWriter().write("用户数据错误!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
