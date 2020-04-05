package vip.designpattern.singleton;

import vip.designpattern.singleton.ThreadLocal.ThreadLocalSingleton;

/**
 * Create by aqqje on 2020/2/29.
 */
public class ThreadLocalSingletonTest {
    public static void main(String[] args) {
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        Thread thread1 = new Thread(new ExectorThread());
        thread1.start();
        ThreadLocalTest2();
    }

    public static void ThreadLocalTest2(){
        System.out.println("ThreadLocalTest2"+ThreadLocalSingleton.getInstance());
        System.out.println("ThreadLocalTest2"+ThreadLocalSingleton.getInstance());
        System.out.println("ThreadLocalTest2"+ThreadLocalSingleton.getInstance());
        System.out.println("ThreadLocalTest2"+ThreadLocalSingleton.getInstance());
    }
}
