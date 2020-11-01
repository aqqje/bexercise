package spring.framework.beans.support;

import spring.framework.beans.GPBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GPBeanDefinitionReader {

    private List<String> registyBeanClasses = new ArrayList<String>();
    private Properties config = new Properties();
    private final String SCAN_PACKAGE = "scanPackage";

    public GPBeanDefinitionReader(String... loactions) {
        // 通过URL定位找到其所对应的文件, 然后转换为文件流
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(loactions[0].replace("classpath:", ""));
        try{
            config.load(is);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {
        // 转换为文件路径, 实际上就是把.替换为/就OK了
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else{
                if(!file.getName().endsWith(".class")){
                    continue;
                }
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                registyBeanClasses.add(className);
            }
        }
    }

    // 把配置文件中扫描到的所有的配置信息转换为GPBeanfinition对象, 以 以便于之后IOC操作方便
    public List<GPBeanDefinition> loadBeanDefinitons() {
        List<GPBeanDefinition> result = new ArrayList<GPBeanDefinition>();
        try {
            for (String registyBeanClass : registyBeanClasses) {
                Class<?> beanClass = Class.forName(registyBeanClass);
                // 接口类直接路过
                if(beanClass.isInterface()){
                    continue;
                }
                /*
                * beanName 三种情况
                * 1 默认类名首字母小写
                * 2 自定义名字
                * 3 接口注入
                * */
                result.add(doCreateBeanDefinition(toLowerFistCase(beanClass.getSimpleName()), beanClass.getName()));
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> anInterface : interfaces) {
                    /*
                    * 如果是多个实现类,只能覆盖
                    * 为什么? 因为Spring没那么智能, 就是这么傻
                    * 这个时候, 可以自定义名字
                    * */
                    result.add(doCreateBeanDefinition(anInterface.getName(), beanClass.getName()));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    // 把每一个配信息解析成一个BeanDefinition
    private GPBeanDefinition doCreateBeanDefinition(String factoryName, String beanClassName) {
        GPBeanDefinition beanDefinition = new GPBeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryName);
        return beanDefinition;
    }

    private String toLowerFistCase(String simpleName){
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
