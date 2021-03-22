package com.demo.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器式单列: 有缺点不是线程安全的
 */
public class ContainerSingleton {

    private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>();

    private ContainerSingleton(){}

    public static Object getInstance(String className){
        Object instance = null;
        try {
            if(!ioc.containsKey(className)){
                instance = Class.forName(className);
                ioc.put("className", instance);
            } else {
                instance = ioc.get(className);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}
