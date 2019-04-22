package cn.aqqje.web.filter;

import cn.aqqje.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/*拦所有请求*/
@WebFilter("/*")
public class loginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        /*获取路径*/
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if(uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/checkCodeServlet") || uri.contains("/RESOURCES/css/") || uri.contains("/RESOURCES/js/") ){/*需要放行的资源*/
            filterChain.doFilter(req,res);
        }else{
            User loginUser = (User)request.getSession().getAttribute("loginUser");
            if(loginUser != null){
                filterChain.doFilter(req,res);
            }else{
                request.setAttribute("loginErrorMsg", "您未登录,请登录!");
                request.getRequestDispatcher("/login.jsp").forward(request,res);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
