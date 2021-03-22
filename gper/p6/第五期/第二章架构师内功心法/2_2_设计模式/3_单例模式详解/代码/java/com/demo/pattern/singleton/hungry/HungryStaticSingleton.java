package com.demo.pattern.singleton.hungry;

/**
 * 饿汉式单例的第二种写法（静态代码块）
 */
public class HungryStaticSingleton {
    /**
     * 静态属性（单例本神）随着类的加载而加载
     */
    private static final HungryStaticSingleton hungryStaticSingleton;

    /**
     * 静态代码块随着类的加载而加载
     *
     * 类的加载顺序： 先静态后动态， 先上后下，选择属性后方法
     */
    static {
        hungryStaticSingleton = new HungryStaticSingleton();
    }

    /**
     * 构造器私有化
     */
    private HungryStaticSingleton(){}

    /**
     * 给单例提供一个全局访问点
     * @return 单例的实例对象
     */
    public static HungryStaticSingleton getInstance() {
        return hungryStaticSingleton;
    }
}
