package com.demo.pattern.singleton.threadlocal;

/**
 * ThreadLocal 单例
 * 保证线程内部的全局唯一， 且天生线程安全。
 *
 * 只能保存同一个线程下的实例唯一，多线程下非单例
 */
public class ThreadLocalSingleton {

    private static ThreadLocal<ThreadLocalSingleton> threadLocal = new ThreadLocal<ThreadLocalSingleton>(){
        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    private ThreadLocalSingleton(){}

    public static ThreadLocalSingleton getInstance(){
        return threadLocal.get();
    }
}
