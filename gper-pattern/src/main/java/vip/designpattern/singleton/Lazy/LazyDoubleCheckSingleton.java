package vip.designpattern.singleton.Lazy;

/**
 * 优点: 性能高，线程安全
 * 缺点：代码不够优雅，可读性难度加大
 * Create by aqqje on 2020/2/29.
 */
public class LazyDoubleCheckSingleton {
    private volatile static LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton(){};

    public static LazyDoubleCheckSingleton getInstance(){
        // 检查是否阻塞
        if(instance == null){
            synchronized (LazyDoubleCheckSingleton.class){
                // 检查是否重新创建
                if(instance == null){
                    instance = new LazyDoubleCheckSingleton();
                    // 指令重排序的问题(volatile 关键字来处理)
                }
            }
        }
        return instance;
    }
}
