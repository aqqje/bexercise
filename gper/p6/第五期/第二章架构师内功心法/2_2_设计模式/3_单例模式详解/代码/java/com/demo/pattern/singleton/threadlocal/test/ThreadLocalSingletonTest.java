package com.demo.pattern.singleton.threadlocal.test;

import com.demo.pattern.singleton.threadlocal.ThreadLocalSingleton;

/**
 * Threadlocal 单例测试
 */
public class ThreadLocalSingletonTest {

    public static void main(String[] args) {
        ThreadLocalSingleton instance1 = ThreadLocalSingleton.getInstance();
        ThreadLocalSingleton instance2 = ThreadLocalSingleton.getInstance();

        System.out.println(instance1);
        System.out.println(instance2);

        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());
        t1.start();
        t2.start();
        System.out.println("END");

//        com.demo.pattern.singleton.threadlocal.ThreadLocalSingleton@4dc63996
//        com.demo.pattern.singleton.threadlocal.ThreadLocalSingleton@4dc63996
//        END
//        Thread-0:com.demo.pattern.singleton.threadlocal.ThreadLocalSingleton@70dd2224
//        Thread-1:com.demo.pattern.singleton.threadlocal.ThreadLocalSingleton@59ec5194

    }
}
