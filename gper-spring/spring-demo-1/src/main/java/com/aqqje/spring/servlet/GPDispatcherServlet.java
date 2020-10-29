package com.aqqje.spring.servlet;

import com.aqqje.spring.annotation.*;
import sun.security.util.ArrayUtil;

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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author ouming
 */
public class GPDispatcherServlet extends HttpServlet {

    /** 加载配置 */
    private Properties properties = new Properties();
    /** 存在类名 */
    private List<String> classNames = new ArrayList<String>();
    /** ioc容器 */
    private Map<String, Object> ioc = new HashMap<String, Object>();
    /** 映射地址 */
    private List<Handler> handlerMapping = new ArrayList<Handler>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.doDispatch(req, resp);
        }catch (Exception e){
            e.getMessage();
            resp.getWriter().write("500 !!!");
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Handler handler = this.getHandler(req);
        if(handler == null){
            resp.getWriter().write("404 Not Found!!!");
            return;
        }
        Class<?>[] parameterTypes = handler.getParameterTypes();
        Object[] parameValue = new Object[parameterTypes.length];
        Map<String, String[]> params = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String value = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", "");
            if(!handler.getParameMap().containsKey(entry.getKey())){
                continue;
            }
            Integer index = handler.getParameMap().get(entry.getKey());
            parameValue[index] = this.convert(parameterTypes[index], value);
        }

        if(handler.getParameMap().containsKey(HttpServletRequest.class.getName())){
            Integer index = handler.getParameMap().get(HttpServletRequest.class.getName());
            parameValue[index] = req;
        }

        if(handler.getParameMap().containsKey(HttpServletResponse.class.getName())){
            Integer index = handler.getParameMap().get(HttpServletResponse.class.getName());
            parameValue[index] = resp;
        }
        Object invoke = null;
        try {
            invoke = handler.getMethod().invoke(handler.getController(), parameValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(invoke == null && invoke instanceof Void ){
            return;
        }
        resp.getWriter().write(invoke.toString());

    }

    private Object convert(Class<?> type,String value){
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
        if(handlerMapping.isEmpty()){
            return null;
        }
        String requestURI = req.getRequestURI();
        requestURI =  requestURI.replace(req.getContextPath(), "").replaceAll("/+", "/");
        for (Handler handler : handlerMapping) {
            if(handler.getPattern().matcher(requestURI).matches()){
                return handler;
            }
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 加载配置文件
        this.doLoadConfig(config.getInitParameter("contextConfigLocation"));
        // 扫描包
        this.doScanPackage(properties.getProperty("scanPackage"));
        // 反射实例化, 注入ioc容器
        this.doInstance();
        // 依赖注入
        this.doAutowired();
        // 保证相关地址映射关系
        this.doHandlerMapping();
        // 请求分发
    }

    // 保证相关地址映射关系
    private void doHandlerMapping() {
        if(ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if(!(clazz.isAnnotationPresent(GPController.class) && clazz.isAnnotationPresent(GPRequestMapping.class))){
                continue;
            }
            GPRequestMapping gpRequestMapping1 = clazz.getAnnotation(GPRequestMapping.class);
            String value1 = gpRequestMapping1.value();
            String url = "/" + value1;
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if(!method.isAnnotationPresent(GPRequestMapping.class)){
                    continue;
                }
                GPRequestMapping gpRequestMapping2 = method.getAnnotation(GPRequestMapping.class);
                String value2 = gpRequestMapping2.value();
                url += "/" + value2;
                Pattern pattern = Pattern.compile(url.replaceAll("/+", "/"));
                handlerMapping.add(new Handler(method, entry.getValue(), pattern));
            }

        }
    }

    /** 依赖注入 */
    private void doAutowired(){
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> item : ioc.entrySet()) {
            Class clazz = item.getValue().getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(GPAutowired.class)) {
                    field.setAccessible(true);
                    GPAutowired gpAutowired = field.getAnnotation(GPAutowired.class);
                    String value = gpAutowired.value();
                    Class<?> fieldType = field.getType();
                    if (!value.trim().equals("")) {
                        if (ioc.containsKey(value)) {
                            try {
                                field.set(fieldType, ioc.get(value));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        String name = fieldType.getName();
                        try {
                            field.set(item.getValue(), ioc.get(name));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    /**
     * 反射实例化
     */
    private void doInstance() {
        if(classNames.size() == 0){
            return;
        }
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);

                // controller , service, serviceImpl
                if(clazz.isAnnotationPresent(GPController.class)) {
                    ioc.put(initials2Lowercase(clazz.getSimpleName()), clazz.newInstance());
                } else if (clazz.isAnnotationPresent(GPService.class)) {
                    String simpleName = initials2Lowercase(clazz.getSimpleName());
                    // 判断是否有别名
                    GPService gpService = clazz.getAnnotation(GPService.class);
                    String value = gpService.value();
                    Object o = clazz.newInstance();
                    ioc.put(simpleName, o);
                    if(!value.trim().equals("")){
                        ioc.put(value, o);
                    }
                    // 获取接口
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> anInterface : interfaces) {
                        if(ioc.containsKey(anInterface.getName())){
                            throw new Exception("The “" + clazz.getName() + "” is exists!!");
                        }
                        ioc.put(anInterface.getName(), o);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String initials2Lowercase(String simpleName){
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    /**
     * 扫描包
     */
    private void doScanPackage(String dir) {
        URL url = this.getClass().getClassLoader().getResource("/" + dir.replaceAll("\\.", "/"));
        File fileDir = new File(url.getFile());
        File[] files = fileDir.listFiles();
        for (File file : files) {
            String className = file.getName();
            if(file.isDirectory()){
                this.doScanPackage(dir + "." + className);
            }
            if(!className.endsWith(".class")){
                continue;
            }
            String classPath = dir + "." + className.replace(".class", "");
            classNames.add(classPath);
        }
    }

    /**
     * 加载配置文件
     */
    private void doLoadConfig(String contextConfigLocation) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null){
                try {
                    is.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
    }

    public class Handler{
        private Method method;
        private Object controller;
        private Pattern pattern;
        private Class<?>[] parameterTypes;

        public Map<String, Integer> getParameMap() {
            return parameMap;
        }

        public void setParameMap(Map<String, Integer> parameMap) {
            this.parameMap = parameMap;
        }

        private Map<String, Integer> parameMap = new HashMap<String, Integer>();
        public Handler(Method method, Object controller, Pattern pattern) {
            this.method = method;
            this.controller = controller;
            this.pattern = pattern;
            this.parameterTypes = this.getMethod().getParameterTypes();
            this.initParameMap(method);
        }

        private void initParameMap(Method method) {
            Annotation[][] pa = method.getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if(a instanceof GPRequestParam){
                        String value = ((GPRequestParam) a).value();
                        parameMap.put(value, i);
                    }
                }
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameterType = parameterTypes[i];
                if(parameterType == HttpServletRequest.class || parameterType == HttpServletResponse.class){
                    parameMap.put(parameterType.getTypeName(), i);
                }
            }
        }

        public Class<?>[] getParameterTypes() {
            return parameterTypes;
        }

        public void setParameterTypes(Class<?>[] parameterTypes) {
            this.parameterTypes = parameterTypes;
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
            this.controller = controller;
        }
    }
}

