package com.demo.pattern.singleton.lazy.test;

/**
 * 懒汉式单例线程不安全测试
 */
public class LazySimpleSingletonTest {


    public static void main(String[] args) {
        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());
        t1.start();
        t2.start();
        System.out.println("End");
    }
}
