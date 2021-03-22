package com.demo.pattern.singleton.lazy;

/**
 * 懒汉式单例：在外部调用的时间进行加载
 *
 * 优点： 节省内存
 * 缺点： 线程不安全
 */
public class LazySimpleSingleton {

    /**
     * 单例实例属性
     */
    private static LazySimpleSingleton instance;

    /**
     * 构造器私有化
     */
    private LazySimpleSingleton(){}

    /**
     * 给单例提供一个全局访问点
     * @return 单例的实例对象
     *
     * 线程不安全
     */
    /*public static LazySimpleSingleton getInstance() {
        // 第一次调用的时间才创建对象
        if(null == instance){
            instance = new LazySimpleSingleton();
        }
        return instance;
    }*/


    /**
     * 给单例提供一个全局访问点
     * @return 单例的实例对象
     *
     * 线程安全, 性能下降
     *
     */
    public synchronized static LazySimpleSingleton getInstance() {
        // 第一次调用的时间才创建对象
        if(null == instance){
            instance = new LazySimpleSingleton();
        }
        return instance;
    }
}
