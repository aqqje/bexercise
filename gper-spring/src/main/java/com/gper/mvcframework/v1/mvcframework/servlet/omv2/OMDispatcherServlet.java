package com.gper.mvcframework.v1.mvcframework.servlet.omv2;

import com.gper.mvcframework.v1.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class OMDispatcherServlet extends HttpServlet {
    private Properties contextConfig = new Properties();

    //享元模式，缓存
    private List<String> classNames = new ArrayList<String>();

    //IoC容器，key默认是类名首字母小写，value就是对应的实例对象
    private Map<String,Object> ioc = new HashMap<String,Object>();

    private Map<String,Method> handlerMapping = new HashMap<String, Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //6、委派,根据URL去找到一个对应的Method并通过response返回
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception,Detail : " + Arrays.toString(e.getStackTrace()));
        }

    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        uri = uri.replace(contextPath, "").replaceAll("/+", "/");

        if(!handlerMapping.containsKey(uri)){
            resp.getWriter().write("404 Not Found!!!");
            return;
        }

        Map<String, String[]> parameterMap = req.getParameterMap();

        Method method = (Method)this.handlerMapping.get(uri);

        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] parameterValues = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            Class paramterType = parameterTypes[i];
            if(paramterType == HttpServletRequest.class){
                parameterValues[i] = req;
            }else if(paramterType == HttpServletResponse.class){
                parameterValues[i] = resp;
            } else if(paramterType == String.class){
                // 获取运行注解对象
                Annotation[][] pa = method.getParameterAnnotations();
                for (int j = 0; j < pa.length; j++) {
                    for (Annotation a : pa[i]) {
                        if(a instanceof GPRequestParam){
                            String paramName = ((GPRequestParam) a).value();
                            if(!"".equals(paramName.trim())){
                                String value = Arrays.toString(parameterMap.get(paramName)).replaceAll("\\[|\\]","")
                                        .replaceAll("\\s+",",");
                                parameterValues[i] = value;
                            }
                        }
                    }
                }
            }
        }
        //暂时硬编码
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        //赋值实参列表
        method.invoke(ioc.get(beanName),parameterValues);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1、加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2、扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        //==============IoC部分==============
        //3、初始化IoC容器，将扫描到的相关的类实例化，保存到IcC容器中
        doInstance();

        //AOP，新生成的代理对象

        //==============DI部分==============
        //4、完成依赖注入
        doAutowired();

        //==============MVC部分==============
        //5、初始化HandlerMapping
        doInitHandlerMapping();

        System.out.println("GP Spring framework is init.");
    }

    private void doInitHandlerMapping() {
        if(ioc.isEmpty()){return;}
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(GPController.class)){continue;}

            String baseUrl = "";
            if(clazz.isAnnotationPresent(GPRequestMapping.class)){
                baseUrl = clazz.getAnnotation(GPRequestMapping.class).value();
            }

            for (Method method : clazz.getMethods()) {
                if(!method.isAnnotationPresent(GPRequestMapping.class)){continue;}
                String url = ("/" + baseUrl + "/" + method.getAnnotation(GPRequestMapping.class).value()).replaceAll("/+", "/");
                handlerMapping.put(url, method);
                System.out.println("Mapped : " + url + "," + method);
            }
        }
    }

    private void doAutowired() {
        if(ioc.isEmpty()){return;}
        try {
            for (Map.Entry<String, Object> entry : ioc.entrySet()) {
                for (Field field : entry.getValue().getClass().getDeclaredFields()) {
                    if(!field.isAnnotationPresent(GPAutowired.class)){continue;}
                    GPAutowired gpAutowired = field.getAnnotation(GPAutowired.class);
                    String beanName = gpAutowired.value().trim();
                    if("".equals(beanName)){
                        beanName = field.getType().getName();
                    }
                    field.setAccessible(true);
                    field.set(entry.getValue(), ioc.get(beanName));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("DI error!!!");
        }
    }

    private void doInstance() {
        if(classNames.isEmpty()){return;}
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);

                if(clazz.isAnnotationPresent(GPController.class)){
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, clazz.newInstance());
                }else if(clazz.isAnnotationPresent(GPService.class)){

                    GPService gpService = clazz.getAnnotation(GPService.class);
                    String beanName  = gpService.value();
                    if("".equals(beanName)){beanName = toLowerFirstCase(clazz.getSimpleName());}
                    Object newInstance = clazz.newInstance();
                    ioc.put(beanName, newInstance);
                    for (Class<?> anInterface : clazz.getInterfaces()) {
                        if(ioc.containsKey(anInterface.getName())){
                            throw new Exception("The " + anInterface.getName() + " is exists!!");
                        }
                        ioc.put(anInterface.getName(), newInstance);
                    }

                }else{continue;}
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("doInstance error!!!");
        }

    }

    //自己写，自己用
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if(file.isDirectory()){doScanner(scanPackage + "." + file.getName());}else
            {if(!file.getName().endsWith(".class")){continue;}
                String className = scanPackage + "." + file.getName().replace(".class", "");
                classNames.add(className);}
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
