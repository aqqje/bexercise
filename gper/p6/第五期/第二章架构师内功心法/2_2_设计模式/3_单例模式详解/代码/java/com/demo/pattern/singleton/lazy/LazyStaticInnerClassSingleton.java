package com.demo.pattern.singleton.lazy;

/**
 * 懒汉式：静态内部类形式
 *
 */
public class LazyStaticInnerClassSingleton {

    /**
     * 构造器私有化
     */
    private LazyStaticInnerClassSingleton(){
        // 处理反射破坏
        if(LazyHolder.INSTANCE != null){
            throw new RuntimeException("不允许非法访问");
        }
    };

    /**
     * 给单例提供一个全局访问点
     * @return 单例的实例对象
     *
     */
    public static LazyStaticInnerClassSingleton getInstance(){
        return LazyHolder.INSTANCE;
    }

    /**
     * 静态内部类：不会像静态的成员变量在加载的时间就已经创建， 而静态内部类只有在调用的时候才会被创建， 所以就是按需加载 => 懒汉式
     *
     * ClassPath : LazyStaticInnerClassSingleton.class 先加载
     *             LazyStaticInnerClassSingleton.class$LazyHolder.class 被使用才加载
     * 优点： 写法优雅， 利用了Java本身特点， 性能高， 避免了内存浪费
     * 缺点： 能够被反射破坏
     */
    private static class LazyHolder{
        private static final LazyStaticInnerClassSingleton INSTANCE = new LazyStaticInnerClassSingleton();
    }
}
