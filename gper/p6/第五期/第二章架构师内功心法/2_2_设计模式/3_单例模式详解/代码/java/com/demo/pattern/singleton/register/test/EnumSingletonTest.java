package com.demo.pattern.singleton.register.test;

import com.demo.pattern.singleton.register.EnumSingleton;

import java.lang.reflect.Constructor;

/**
 * 枚举式单身例测试
 */
public class EnumSingletonTest {

    public static void main(String[] args) {
        EnumSingleton instance = EnumSingleton.getInstance();
        instance.setData(new Object());

        try {

            Class<?> clazz = EnumSingleton.class;
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class, int.class);
            declaredConstructor.setAccessible(true);
            // 不能使用枚举创建对象 | java.lang.IllegalArgumentException: Cannot reflectively create enum objects
            Object o = declaredConstructor.newInstance();
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
