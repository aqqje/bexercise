package com.demo.pattern.proxy.dynamicproxy.cglibproxy;

import com.demo.pattern.proxy.dynamicproxy.jdkproxy.IPerson;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CglibMeipo implements MethodInterceptor {

    public Object getInstance(Class<?> clazz){
        // 相当于proxy, 生成代码
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }


    private void after() {
        System.out.println("开始交往");
    }

    private void before() {
        System.out.println("我是媒婆，已经收集到你的需求，开始物色");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        after();
        methodProxy.invokeSuper(o, objects);
        before();
        return null;
    }
}
