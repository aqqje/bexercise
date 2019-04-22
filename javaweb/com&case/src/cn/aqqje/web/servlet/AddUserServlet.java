package cn.aqqje.web.servlet;

import cn.aqqje.domain.User;
import cn.aqqje.service.UserService;
import cn.aqqje.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*设置字符集*/
        request.setCharacterEncoding("utf-8");
        /*获取用户信息*/
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            /*封装对象*/
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if(user != null){
            service.addUser(user);
            response.setContentType("text/html;charset=utf-8");
            response.sendRedirect(request.getContextPath()+"/userListServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
