package com.demo.pattern.singleton.hungry;

/**
 * 饿汉式单例
 * 优点： 执行效率高， 性能高， 没有任务的锁
 * 缺点： 某些情况下， 可能会造成内存浪费
 */
public class HungrySingleton {
    /**
     * 静态成员（单例本神）随着类的加载而加载
     */
    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    /**
     * 构造器私有化
     */
    private HungrySingleton(){}

    /**
     * 给单例提供一个全局访问点
     * @return 单例的实例对象
     */
    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}
