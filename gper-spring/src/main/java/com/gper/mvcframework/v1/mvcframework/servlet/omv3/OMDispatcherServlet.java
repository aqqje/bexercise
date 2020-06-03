package com.gper.mvcframework.v1.mvcframework.servlet.omv3;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OMDispatcherServlet extends HttpServlet {
    private Properties contextConfig = new Properties();

    //享元模式，缓存
    private List<String> classNames = new ArrayList<String>();

    //IoC容器，key默认是类名首字母小写，value就是对应的实例对象
    private Map<String,Object> ioc = new HashMap<String,Object>();

    // private Map<String,Method> handlerMapping = new HashMap<String, Method>();

    private List<Handler> handlerMapping = new ArrayList<Handler>();

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
        Handler handler = getHandler(req);
        if(null == handler){
            resp.getWriter().write("404 Not Found!!!");
            return;
        }
        // 形参列表
        Class<?>[] paramTypes = handler.getParamTypes();
        Object[] paramValues = new Object[paramTypes.length];
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
            System.out.println(Arrays.toString(param.getValue()));
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", "");
            if(!handler.paramIndexMapping.containsKey(param.getKey())){return;}
            int index = handler.paramIndexMapping.get(param.getKey());
            paramValues[index] = convert(paramTypes[index], value);
        }
        if(handler.paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }

        if(handler.paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }
        Object returnValue = handler.method.invoke(handler.controller,paramValues);
        if(returnValue == null || returnValue instanceof Void){ return; }
        resp.getWriter().write(returnValue.toString());
    }
    //url传过来的参数都是String类型的，HTTP是基于字符串协议
    //只需要把String转换为任意类型就好
    private Object convert(Class<?> type, String value){
        //如果是int
        if(Integer.class == type){
            return Integer.valueOf(value);
        }
        else if(Double.class == type){
            return Double.valueOf(value);
        }
        //如果还有double或者其他类型，继续加if
        //这时候，我们应该想到策略模式了
        //在这里暂时不实现，希望小伙伴自己来实现
        return value;
    }

    private Handler getHandler(HttpServletRequest req) {
        if(handlerMapping.isEmpty()){return null;}
        //获取绝对路径
        String url = req.getRequestURI();
        //获取上下文路径
        String contextPath = req.getContextPath();
        // 路径处理
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");
        // 遍历获取 handler
        for (Handler handler : this.handlerMapping) {
            Matcher matcher = handler.pattern.matcher(url);
            if(!matcher.matches()){continue;}
            return handler;
        }
        return null;
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
                Pattern pattern = Pattern.compile(url);
                handlerMapping.add(new Handler(pattern, method, entry.getValue()));
                System.out.println("Mapped : " + pattern + "," + method);
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

    /**handler 映射关系 */
    public class Handler{
        // 映射路径
        public Pattern pattern;
        // 方法
        private Method method;
        // 对象
        private Object controller;
        // 形参列表类型
        private Class<?>[] paramTypes;
        //参数的名字作为key,参数的顺序，位置作为值
        private Map<String, Integer> paramIndexMapping = new HashMap<String, Integer>();

        public Handler(Pattern pattern, Method method, Object controller) {
            this.pattern = pattern;
            this.method = method;
            this.controller = controller;
            this.paramTypes = method.getParameterTypes();
            paramIndexMapping = new HashMap<String, Integer>();
            pubParamIndexMapping(method);
        }

        private void pubParamIndexMapping(Method method) {
            Annotation[][] pa = method.getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if(a instanceof GPRequestParam){
                        String param = ((GPRequestParam) a).value();
                        if(!"".equals(param.trim())){
                            paramIndexMapping.put(param, i);
                        }
                    }
                }
            }

            // 提取 request / response
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> type = parameterTypes[i];
                if(type == HttpServletRequest.class || type == HttpServletResponse.class){
                    paramIndexMapping.put(type.getName(), i);
                }
            }
        }

        public Class<?>[] getParamTypes() {
            return paramTypes;
        }

        public void setParamTypes(Class<?>[] paramTypes) {
            this.paramTypes = paramTypes;
        }

        public Pattern getPattern() {
            return pattern;
        }

        public void setPattern(Pattern pattern) {
            this.pattern = pattern;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            controller = controller;
        }
    }
}