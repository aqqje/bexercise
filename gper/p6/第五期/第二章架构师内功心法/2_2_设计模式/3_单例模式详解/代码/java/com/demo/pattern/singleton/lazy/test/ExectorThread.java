package com.demo.pattern.singleton.lazy.test;

import com.demo.pattern.singleton.lazy.LazySimpleSingleton;

/**
 * 懒汉式单例线程不安全测试线程
 */
public class ExectorThread implements Runnable {

    @Override
    public void run() {
        LazySimpleSingleton instance = LazySimpleSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + instance);
    }
}
