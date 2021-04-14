package com.spring.framework;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GPDispatcherServlet extends HttpServlet {

    private static Properties properties;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 6, 请求分发
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1, 加载配置文件
        dolocalConfig();
        String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 2, 扫描包
        doScanPackage();
        // 3, 生成实例
        doInstance();
        // 4, 依赖注入
        doAutowired();
        // 5, 处理url与方法的映射关系
        doHaddlerMapping();
        System.out.println("spring framework init finish ...");
    }
}
