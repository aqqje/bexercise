package com.demo.pattern.singleton.lazy.test;

import com.demo.pattern.singleton.lazy.LazyStaticInnerClassSingleton;

import java.lang.reflect.Constructor;

/**
 * 单例被反射破坏示例
 */
public class ReflectTest {

    public static void main(String[] args) {
        try {
            Class<?> clazz = LazyStaticInnerClassSingleton.class;
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(null);

            // 强制访问
            declaredConstructor.setAccessible(true);

            Object instance1 = declaredConstructor.newInstance();
            Object instance2 = declaredConstructor.newInstance();

            System.out.println(instance1);
            System.out.println(instance2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
