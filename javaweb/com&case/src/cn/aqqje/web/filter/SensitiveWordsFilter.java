package cn.aqqje.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    private List<String> mgch = new ArrayList<>();
    public void init(FilterConfig config) throws ServletException {
        try{
            // 获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String path = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            // 读取文件
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
            // 将文件内容写入list集体中
            String line = null;
            while((line = br.readLine()) != null){
                mgch.add(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ServletRequest proxy_req = (ServletRequest)Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("getParameter")){
                    String value = (String)method.invoke(req,args);
                    if(value != null){
                        for (String str: mgch){
                            if(value.contains(str)){
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(req, args);
            }
        });
        /*放行*/
        chain.doFilter(proxy_req, resp);
    }

    public void destroy() {
    }
}
