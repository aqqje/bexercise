package com.demo.pattern.lazy;

/**
 * 懒汉式单例线程不安全测试
 */
public class LazySimpleSingletonTest {


    public static void main(String[] args) {
        Thread t1 = new Thread(new ExectorThred());
        Thread t2 = new Thread(new ExectorThred());
        t1.start();
        t2.start();
        System.out.println("End");
    }
}
