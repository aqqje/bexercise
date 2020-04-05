package vip.designpattern.singleton;

import vip.designpattern.singleton.Lazy.LazySimpleSingleton;
import vip.designpattern.singleton.ThreadLocal.ThreadLocalSingleton;

/**
 * Create by aqqje on 2020/2/29.
 */
public class ExectorThread implements Runnable {
    public void run() {
//        LazySimpleSingleton instance = LazySimpleSingleton.getInstance();
//        System.out.println(Thread.currentThread().getName() + ":" +instance );
        System.out.println("ExectorThread"+ ThreadLocalSingleton.getInstance());
    }
}
