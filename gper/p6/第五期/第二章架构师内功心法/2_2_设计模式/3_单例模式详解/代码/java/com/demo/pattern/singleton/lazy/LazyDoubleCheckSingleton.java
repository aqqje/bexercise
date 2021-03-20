package com.demo.pattern.singleton.lazy;

/**
 * 懒汉式：双重检测锁
 *
 * 优点： 性能高了，线程安全
 * 缺点： 可读性难度加大，不够优雅
 */
public class LazyDoubleCheckSingleton {
    /**
     * 单例实例属性
     *
     * volatile 关键字可以解决指令重排序的问题
     */
    private volatile static LazyDoubleCheckSingleton instance;

    /**
     * 构造器私有化
     */
    private LazyDoubleCheckSingleton(){}

    /**
     * 给单例提供一个全局访问点
     * @return 单例的实例对象
     *
     */
    public static LazyDoubleCheckSingleton getInstance() {
        // 第一次调用的时间才创建对象
        // 检查是否要阻塞
        if(null == instance){
            synchronized (LazyDoubleCheckSingleton.class){
                // 检查是否要重新创建实例
                if(null == instance) {
                    instance = new LazyDoubleCheckSingleton();
                    // 双重检查锁=>指令重排序的问题
                }
            }
        }

        return instance;
    }
}
