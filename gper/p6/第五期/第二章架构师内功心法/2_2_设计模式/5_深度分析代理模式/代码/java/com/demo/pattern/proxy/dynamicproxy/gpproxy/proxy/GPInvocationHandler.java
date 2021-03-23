package com.demo.pattern.proxy.dynamicproxy.gpproxy.proxy;

import java.lang.reflect.Method;

public interface GPInvocationHandler {

    Object invoke(Object GPProxy, Method method, Object[] args)
            throws Throwable;
}
