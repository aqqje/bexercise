package com.demo.pattern.singleton.register;

/**
 * 枚举式单例
 *
 * 类似与饿汉写法： 某些情况下， 可能会造成内存浪费
 */
public enum  EnumSingleton {

    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
