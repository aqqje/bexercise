package vip.designpattern.singleton.Seriable;

import java.io.Serializable;

/**
 * Create by aqqje on 2020/2/29.
 */
public class SeriableSingleton implements Serializable {

    // 序列化
    // 把内存中对象的状态转换为字节码的形式
    // 把字节码通过IO输出流，写到磁盘上
    // 永久保留下来，持久化

    // 反序列化
    // 将持久化的字节码内存，通过IO输入流读到内存中来
    // 转化成一个java对象

    private static final SeriableSingleton INSTANCE = new SeriableSingleton();

    private SeriableSingleton(){};

    public static SeriableSingleton getInstance(){return INSTANCE;}

    /** 解决序列化破坏单单例情况*/
    private Object readResolve(){return INSTANCE;}
}
