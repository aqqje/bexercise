package cn.instroduction.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {
    private InputStream in;
    private static Properties props;
    private String filepath;

    public BeanFactory(String filepath) throws IOException {
        this.filepath = filepath;
        builder();
    }

    private void builder() throws IOException {
        props = new Properties();
        in = BeanFactory.class.getClassLoader().getResourceAsStream(filepath);
        props.load(in);
    }

    public static Object getBean(String beanName) {
        // 获取全限定类名
        Object obj = null;
        String className = (String) props.get(beanName);
        try {
            obj = Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("bean 创建失败!");
        }
        return obj;
    }
}
