package com.demo.pattern.singleton.threadlocal.test;

import com.demo.pattern.singleton.threadlocal.ThreadLocalSingleton;

public class ExectorThread implements Runnable {
    @Override
    public void run() {
        ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + instance);
    }
}
