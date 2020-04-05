package vip.designpattern.singleton.Lazy;

/**
 * 优点：节省内存
 * 缺点：线程不安全(加锁处理)
 * Create by aqqje on 2020/2/29.
 */
public class LazySimpleSingleton {
    private static LazySimpleSingleton instance;

     private LazySimpleSingleton(){};

     public static synchronized LazySimpleSingleton getInstance(){
         if(instance == null){
             instance = new LazySimpleSingleton();
         }
         return instance;
     }
}
