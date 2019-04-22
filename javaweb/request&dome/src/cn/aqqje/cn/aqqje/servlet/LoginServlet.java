package cn.aqqje.cn.aqqje.servlet;

import cn.aqqje.cn.aqqje.dao.UserDao;
import cn.aqqje.cn.aqqje.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        /*
       方法一(不推荐)
        // 获取登录参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        // 封装登录参数
        User loginUser = new User(name, password);
        UserDao dao = new UserDao();
        User login = dao.login(loginUser);*/
        // 检验

        Map<String, String[]> parameterMap = request.getParameterMap();
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, parameterMap);
            System.out.println(loginUser);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        if(user != null){
            request.setAttribute("login", user);
            request.getRequestDispatcher("/user/loginSuccess").forward(request, response);
        }else{
            request.getRequestDispatcher("/user/loginError").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
