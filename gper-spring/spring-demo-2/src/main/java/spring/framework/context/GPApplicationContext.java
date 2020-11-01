package spring.framework.context;

import spring.framework.annotation.GPAutowired;
import spring.framework.annotation.GPController;
import spring.framework.annotation.GPService;
import spring.framework.beans.GPBeanDefinition;
import spring.framework.beans.GPBeanWrapper;
import spring.framework.beans.support.GPBeanDefinitionReader;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author AqqJE
 */
public class GPApplicationContext {

    // 存储注册信息的BeanDfinition
    protected final Map<String, GPBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, GPBeanDefinition>();
    private String[] configLoactions;
    private GPBeanDefinitionReader reader;

    public Map<String, GPBeanDefinition> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }

    // 单例的IOC容器缓存
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();
    // 通道IOC容器
    private Map<String, GPBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String, GPBeanWrapper>();

    public GPApplicationContext(String... configLoactions){
        this.configLoactions = configLoactions;
        try{
            // 1 定位, 定位配置文件
            reader = new GPBeanDefinitionReader(this.configLoactions);
            // 2 加载配置文件, 扫描相关的类, 把它们封闭成 BeanDefinition
            List<GPBeanDefinition> beanDefinitions = reader.loadBeanDefinitons();
            // 3 注册, 把配置信息放到容器里面(伪IOC容器)
            doRegisterBeanDefinition(beanDefinitions);
            // 4 完成自动依赖注入
            doAutowrited();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 只处理非延时加载的情况
    private void doAutowrited() {
        for (Map.Entry<String, GPBeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            try {
                getBean(beanName);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void doRegisterBeanDefinition(List<GPBeanDefinition> beanDefinitions) throws Exception {
        for (GPBeanDefinition beanDefinition : beanDefinitions) {
            if(this.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("The " + beanDefinition.getFactoryBeanName() + " is exists !!");
            }
            this.beanDefinitionMap.put(beanDefinition.getBeanClassName(), beanDefinition);
            this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    public Object getBean(Class<?> beanClass){
        return getBean(beanClass.getName());
    }

    /**
     * 依赖注入从这里开始,通过读取BeanDefinition中的信息
     * 然后通过反射机制创建一个实例并返回
     * Spring做法是不会把最原始的对象放出来,会用一个BeanWraper来进行一次包装
     * 装饰器模式:
     *      1, 保留原来的OOP关
     *      2, 需要对它进行扩展, 增强(为了以后AOP打基础)
     *
     * @param beanName
     *          bean的实例名称
     * @return Object
     *          bean实例
     */
    public Object getBean(String beanName){
        // 1 读取配置信息
        GPBeanDefinition gpBeanDefinition = this.beanDefinitionMap.get(beanName);
        Object instance = null;
        // 2 实例化
        instance = instantiateBean(beanName, gpBeanDefinition);
        // 3 把这个对象封装到BeanWapper中
        GPBeanWrapper beanWrapper = new GPBeanWrapper(instance);
        // 4 把BeanWrapper保存到IOC容器中去
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);
        // 5 执行依赖注入
        populateBean(beanName, new GPBeanDefinition(), beanWrapper);
        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    private Object instantiateBean(String beanName, GPBeanDefinition beanDefinition) {
        // 1 拿到要实例的对象的类名
        String className = beanDefinition.getBeanClassName();
        // 2 反射实例化, 得到一个对象
        Object instance = null;
        try{
            if(this.factoryBeanObjectCache.containsKey(beanName)){
                instance = this.factoryBeanObjectCache.get(className);
            }else{
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.factoryBeanObjectCache.put(beanName, instance);
                this.factoryBeanObjectCache.put(beanDefinition.getFactoryBeanName(), instance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return instance;
    }

    private void populateBean(String beanName, GPBeanDefinition beanDefinition, GPBeanWrapper beanWrapper) {
        Object instannce = beanWrapper.getWrappedInstance();
        Class<?> clazz = beanWrapper.getWrappedClass();
        // 判断只有加了注解的类, 才执行依赖注入
        if(!(clazz.isAnnotationPresent(GPController.class) || clazz.isAnnotationPresent(GPService.class))){
            return;
        }
        // 获取所有的fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if(!field.isAnnotationPresent(GPAutowired.class)){
                continue;
            }
            GPAutowired autowired = field.getAnnotation(GPAutowired.class);
            String autowiredBeanName = autowired.value().trim();
            if("".equals(autowiredBeanName)){
                autowiredBeanName = field.getType().getName();
            }
            // 强制访问
            field.setAccessible(true);
            if(this.factoryBeanInstanceCache.get(autowiredBeanName) == null){
                continue;
            }
            try {
                field.set(instannce, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public String[] getBeanDefinitonNames(){
        return this.getBeanDefinitionMap().keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount(){
        return this.beanDefinitionMap.size();
    }
}
