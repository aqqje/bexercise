package com.demo.pattern.delegate.mvc;

import com.demo.pattern.delegate.mvc.controllers.MemberController;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private Map<String, Method> hadlerMapping = new HashMap<String, Method>();

    @SneakyThrows
    @Override
    public void init() throws ServletException {
        hadlerMapping.put("/web/getMemberById.json", MemberController.class.getMethod("getMemberById", new Class[]{String.class}));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        String url = req.getRequestURI();
        Method method = hadlerMapping.get(url);
//        method.invoke();

    }
}
