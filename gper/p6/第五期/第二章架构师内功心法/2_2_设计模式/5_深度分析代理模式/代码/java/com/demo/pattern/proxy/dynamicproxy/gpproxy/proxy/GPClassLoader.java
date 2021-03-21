package com.demo.pattern.proxy.dynamicproxy.gpproxy.proxy;

public class GPClassLoader extends  ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
