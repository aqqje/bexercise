package vip.designpattern.singleton;

import vip.designpattern.singleton.Lazy.LazyStaticInnerClassSingleton;

import java.lang.reflect.Constructor;

/**
 * Create by aqqje on 2020/2/29.
 */
public class ReferTest {
    public static void main(String[] args){
        try {
            Class<?> clazz = LazyStaticInnerClassSingleton.class;
            Constructor c = clazz.getDeclaredConstructor(null);
            c.setAccessible(true);
            Object instance = c.newInstance();
            System.out.println(instance);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
