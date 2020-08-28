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
import java.util.regex.Pattern;

/**
 * @Author AqqJe
 * @Date 2020/8/17
 * @Version 1.0
 **/
public class AQDispatcherServlet3 extends HttpServlet {

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
    private List<Handler> handlerMapping = new ArrayList<Handler>();

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
        Handler handler = getHandler(req);
        try {
            if (handler == null) {
                resp.getWriter().write("404 not find!");
                return;
            }

            Map<String, String[]> parameterMap = req.getParameterMap();
            Class<?>[] parameterTypes = handler.getParameterTypes();
            Object[] parameterValues = new Object[parameterTypes.length];
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                if(handler.getParamHandlerMap().containsKey(entry.getKey())){
                    String value = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]", "");
                    parameterValues[handler.getParamHandlerMap().get(entry.getKey())] = value;
                }
            }
            if(handler.getParamHandlerMap().containsKey(HttpServletRequest.class.getName())){
                int index = handler.getParamHandlerMap().get(HttpServletRequest.class.getName());
                parameterValues[index] = req;
            }
            if(handler.getParamHandlerMap().containsKey(HttpServletResponse.class.getName())){
                int index = handler.getParamHandlerMap().get(HttpServletResponse.class.getName());
                parameterValues[index] = resp;
            }
            Object invoke = handler.method.invoke(handler.controller, parameterValues);
            if(invoke instanceof Void || invoke == null){
                return;
            }
            resp.getWriter().write(invoke.toString());


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Handler getHandler(HttpServletRequest req) {
        String url = req.getRequestURI().replaceAll(req.getContextPath(), "").replaceAll("/+", "/");
        for (Handler handler : handlerMapping) {
            if(handler.getPattern().matcher(url).matches()){
                return handler;
            }
        }
        return null;
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
            // 遍历当前ioc窗口中的controller类
            Class<?> clazz = entry.getValue().getClass();
            if(!(clazz.isAnnotationPresent(AQController.class) && clazz.isAnnotationPresent(AQRequestMapping.class))){
                return;
            }
            //遍历当前public方法
            for (Method method : clazz.getMethods()) {
                if(!method.isAnnotationPresent(AQRequestMapping.class)){
                    continue;
                }
                String regex = "/" + clazz.getAnnotation(AQRequestMapping.class).value();
                regex = (regex + "/" + method.getAnnotation(AQRequestMapping.class).value()).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                handlerMapping.add(new Handler(pattern, entry.getValue(), method));
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

    class Handler{
        private Pattern pattern = null;

        private Object controller = null;

        private Method method = null;

        private Class<?>[] parameterTypes = null;

        private Map<String, Integer> paramHandlerMap = new HashMap<String, Integer>();
        public Handler() {
        }

        public Class<?>[] getParameterTypes() {
            return parameterTypes;
        }

        public void setParameterTypes(Class<?>[] parameterTypes) {
            this.parameterTypes = parameterTypes;
        }

        public Map<String, Integer> getParamHandlerMap() {
            return paramHandlerMap;
        }

        public void setParamHandlerMap(Map<String, Integer> paramHandlerMap) {
            this.paramHandlerMap = paramHandlerMap;
        }

        public Handler(Pattern pattern, Object controller, Method method) {
            this.pattern = pattern;
            this.controller = controller;
            this.method = method;
            this.parameterTypes = method.getParameterTypes();
            initParamHandlerMap(method);
        }

        private void initParamHandlerMap(Method method) {

            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class parameterType = parameterTypes[i];
                if(parameterType == HttpServletResponse.class || parameterType == HttpServletRequest.class){
                    this.paramHandlerMap.put(parameterType.getName(), i);
                }
            }

            Annotation[][] pa = method.getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation annotation : pa[i]) {
                    if(annotation instanceof AQRequestParam){
                        String paramName = ((AQRequestParam)annotation).value();
                        if(!"".equals(paramName.trim())){
                            this.paramHandlerMap.put(paramName, i);
                        }
                    }
                }
            }

        }

        public Pattern getPattern() {
            return pattern;
        }

        public void setPattern(Pattern pattern) {
            this.pattern = pattern;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }
    }
}
