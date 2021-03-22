package com.demo.pattern.singleton.Seriable;

import java.io.Serializable;

public class SeriableSingleton implements Serializable {

    /**
     * 序列化：
     * 把内存中对象的状态转换为字节码的形式
     * 把字节码通过IO输出流， 写到磁盘永久保存下来， 持久化
     *
     * 反序列化：
     * 将持久化的字节码内容， 通过IO输入流读到内存中来转化成一个java对象
     */

    private SeriableSingleton(){};

    private final static SeriableSingleton INSTANCE = new SeriableSingleton();

    public static SeriableSingleton getInstance() {
        return INSTANCE;
    }

    /**
     * 解决序列化破坏单例：1， 方法名固点 2，直接返回单例实例对象
     * 桥接模式
     * @return
     */
    private Object readResolve() {
        return INSTANCE;
    }
}
