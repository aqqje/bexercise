package cn.instroduction.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class BeanFactory {
    private InputStream in;
    private static Properties props;
    private String filepath;
    private static Map<String, Object> beans;

    public BeanFactory(String filepath) throws IOException {
        this.filepath = filepath;
        builder();
    }

    private void builder() throws IOException {
        props = new Properties();
        in = BeanFactory.class.getClassLoader().getResourceAsStream(filepath);
        props.load(in);
        beans = new HashMap<>();
        Enumeration keys = props.keys();
        /*遍历枚举*/
        while(keys.hasMoreElements()){
            /*获取出 key*/
            String key = keys.nextElement().toString();
            /*获取 value*/
            String value = (String) props.get(key);
            /*实例化对象*/
            Object obj = null;
            try {
                obj = Class.forName(value).newInstance();
            } catch (Exception e) {
                throw new RuntimeException("实例化错误!");
            }
            beans.put(key,obj);
        }
    }

    public static Object getBean(String beanName) {
       return beans.get(beanName);
    }
}
