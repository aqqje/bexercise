package vip.designpattern.singleton.Lazy;

/**
 * 优点：写法优雅，利用了JAVA本身的语法，性能高，避免了内存浪费
 * 缺点：能够被反射破坏
 * Create by aqqje on 2020/2/29.
 */
public class LazyStaticInnerClassSingleton {

    private LazyStaticInnerClassSingleton(){
        if(LazyHolder.INSTANCE != null){
            throw new RuntimeException("不允许非法访问");
        }
    };

    private static LazyStaticInnerClassSingleton getInstance(){
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder{
        private static final LazyStaticInnerClassSingleton INSTANCE = new LazyStaticInnerClassSingleton();
    }
}
