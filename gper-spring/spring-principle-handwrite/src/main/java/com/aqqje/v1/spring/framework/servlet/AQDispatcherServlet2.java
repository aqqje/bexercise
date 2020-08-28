package com.aqqje.v1.spring.framework.servlet;

import com.aqqje.v1.spring.framework.annotation.*;

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

/**
 * @Author AqqJe
 * @Date 2020/8/17
 * @Version 1.0
 **/
public class AQDispatcherServlet2 extends HttpServlet {

    /**
     * 缓存文件资源
     */
    private Properties properties = new Properties();

    /**
     * 缓存包扫描的类
     */
    private List<String> classNames = new ArrayList<String>();

    /**
     * 缓存url与method的映射关系
     */
    private Map<String, Method> handlerMapping = new HashMap<String, Method>();

    /**
     * IoC容器
     */
    private Map<String, Object> ioc = new HashMap<String, Object>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 分发请求处理
            dispacth(req, resp);
        }catch (Exception e){
            resp.getWriter().write("505 Server Error!");
        }
    }

    private void dispacth(HttpServletRequest req, HttpServletResponse resp) {
        // 匹配请求地址
        String url = req.getRequestURI().replaceAll(req.getContextPath(), "").replaceAll("/+", "/");
        System.out.println(url);
        try {
            if (!handlerMapping.containsKey(url)) {
                resp.getWriter().write("404 not find !");
                return;
            }
            // 组合请求参数
            Map<String, String[]> parameterMap = req.getParameterMap();
            Method method = handlerMapping.get(url);

            Class<?>[] parameterTypes = method.getParameterTypes();
            Object[] parameterValues = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                Class parameterType = parameterTypes[i];
                if(parameterType == HttpServletRequest.class){
                    parameterValues[i] = req;
                }else if(parameterType == HttpServletResponse.class){
                    parameterValues[i] = resp;
                }else if(parameterType == String.class){
                    Annotation[][] pa = method.getParameterAnnotations();
                    for (int j = 0; j < pa.length; j++) {
                        for (Annotation annotation : pa[j]) {
                            if(annotation instanceof AQRequestParam){
                                String paramName = ((AQRequestParam) annotation).value();
                                if(!"".equals(paramName.trim())){
                                    String value = Arrays.toString(parameterMap.get(paramName)).replaceAll("\\[|\\]", "").replaceAll(",", "");
                                    parameterValues[i] = value;
                                }
                            }
                        }
                    }
                }
            }
            String beanName = toLowerFirstChar(method.getDeclaringClass().getSimpleName());
            method.invoke(ioc.get(beanName), parameterValues);
        }catch(Exception e){
            e.printStackTrace();
        }

        // 反射调用
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 加载配置类
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        // 扫描相应的包
        doScanPackage(properties.getProperty("scanpackage"));
        // 实例化对象并实例IoC
        doInstance();
        // 实现DI注入
        doAutowried();
        // 初始化url映射关系
        initHandlerMapping();
    }

    private void initHandlerMapping() {
        if(ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if(!(clazz.isAnnotationPresent(AQController.class) && clazz.isAnnotationPresent(AQRequestMapping.class))){
                continue;
            }
            for (Method method : clazz.getMethods()) {
                String url = clazz.getAnnotation(AQRequestMapping.class).value();
                if(!method.isAnnotationPresent(AQRequestMapping.class)){
                    continue;
                }
                url = ( "/" + url + "/" + method.getAnnotation(AQRequestMapping.class).value()).replaceAll("/+", "/");

                handlerMapping.put(url,method);
            }
        }
    }

    private void doAutowried() {
        if(ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            try {
                for (Field field : entry.getValue().getClass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(AQAutowired.class)) {
                        // 自定义
                        String beanName = field.getAnnotation(AQAutowired.class).value();
                        if (field.getType().isInterface() && "".equals(beanName.trim())) {
                            // 接口类型
                            beanName = field.getType().getName();
                        } else {
                            // 类型
                            beanName = toLowerFirstChar(field.getType().getSimpleName());
                        }
                        field.setAccessible(true);
                        field.set(entry.getValue(), ioc.get(beanName));
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String toLowerFirstChar(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] +=32;
        return String.valueOf(chars);
    }

    private void doInstance() {
        if(classNames.isEmpty()){
            return;
        }

        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(AQController.class)){
                    ioc.put(toLowerFirstChar(clazz.getSimpleName()), clazz.newInstance());
                }else if(clazz.isAnnotationPresent(AQService.class)){
                    AQService aqService = clazz.getAnnotation(AQService.class);
                    String beanName = aqService.value();
                    if("".equals(beanName.trim())){
                        beanName = toLowerFirstChar(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> ainterface : interfaces) {
                        if(ioc.containsKey(ainterface.getName())){
                            throw new Exception("this key" + ainterface.getName() + "is exist");
                        }
                        beanName = ainterface.getName();
                        ioc.put(beanName, instance);
                    }
                }else{
                    continue;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void doScanPackage(String scanpackage) {
        // 获取包路径下的资源
        URL uri = this.getClass().getClassLoader().getResource(scanpackage.replaceAll("\\.", "/"));

        File classPath = new File(uri.getFile());

        for (File file : classPath.listFiles()) {
            if(file.isDirectory()){
                doScanPackage(scanpackage + "." + file.getName());
            }else{
                if(!file.getName().contains(".class")){
                    continue;
                }
                String className = scanpackage + "." + file.getName().replaceAll(".class", "");
                classNames.add(className);
            }
        }

    }

    private void doLoadConfig(String initParameter) {
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(initParameter);
            properties.load(is);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(is != null){
                try {
                    is.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
