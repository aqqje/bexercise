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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();

        // 获取用户输入所有的信息的
        Map<String, String[]> loginUserMap = request.getParameterMap();
        String verifycode = request.getParameter("verifycode");

        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");

        User user = new User();
        try {
            // 封装用户信息
            BeanUtils.populate(user, loginUserMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 校验信息
        User loginUser = service.login(user);
        /*验证码校验*/
        if(verifycode.equalsIgnoreCase(checkcode_server)){

            if(loginUser != null){
                session.setAttribute("loginUser", loginUser);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }else{
                // 转发至login.jsp
                request.setAttribute("loginErrorMsg", "用户名或密码错误!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        }else{
            // 转发至login.jsp
            request.setAttribute("loginErrorMsg", "验证码错误!");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
