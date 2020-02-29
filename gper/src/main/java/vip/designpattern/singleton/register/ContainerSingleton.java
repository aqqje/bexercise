package vip.designpattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by aqqje on 2020/2/29.
 */
public class ContainerSingleton {

    /** 处理被反射破坏单例 */
    private ContainerSingleton(){throw new RuntimeException("不可被实例化");};

    private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>();

    /** 线程不安全*/
    public static Object getInstance(String className){
        Object instance = null;
        if (!ioc.containsKey(className)) {
            try {
                instance = Class.forName(className).newInstance();
                ioc.put(className, instance);
            }catch (Exception e){
                e.printStackTrace();
            }
            return instance;
        }else{
            return ioc.get(className);
        }
    }

    /** 使用双重检查锁处理线程安全*/
    public static Object getInstance2(String className){
        Object instance = null;
        if (!ioc.containsKey(className)) {
            synchronized (ContainerSingleton.class){
                if (!ioc.containsKey(className)) {
                    try {
                        instance = Class.forName(className).newInstance();
                        ioc.put(className, instance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return instance;
                }else{
                    return ioc.get(className);
                }
            }
        }
        return ioc.get(className);
    }

    /** 使用putIfAbsent方法本身处理线程安全*/
    public static Object getInstance3(String className){
        Object instance = null;
        if (!ioc.containsKey(className)) {
            try {
                ioc.putIfAbsent(className, instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ioc.get(className);

    }
}
