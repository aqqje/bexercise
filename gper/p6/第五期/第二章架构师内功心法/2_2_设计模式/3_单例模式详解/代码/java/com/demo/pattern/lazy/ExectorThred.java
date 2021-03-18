package com.demo.pattern.lazy;

/**
 * 懒汉式单例线程不安全测试线程
 */
public class ExectorThred implements Runnable {

    @Override
    public void run() {
        LazySimpleSingleton instance = LazySimpleSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + instance);
    }
}
