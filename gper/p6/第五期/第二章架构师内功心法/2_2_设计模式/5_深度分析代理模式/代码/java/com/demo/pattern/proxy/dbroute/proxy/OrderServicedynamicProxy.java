package com.demo.pattern.proxy.dbroute.proxy;

import com.demo.pattern.proxy.dbroute.db.DynamicDataSourceEntity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderServicedynamicProxy implements InvocationHandler {

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    Object proxyObj;
    public Object getInstance(Object proxyObj){
        this.proxyObj = proxyObj;
        Class<?> clazz = proxyObj.getClass();

        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }


    private void after() {
        System.out.println("Proxy after method");
        DynamicDataSourceEntity.restore();
    }

    private void befor(Object target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Proxy before method");
        long time = (long) target.getClass().getMethod("getCreateTime").invoke(target);
        Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
        System.out.println("动态代理类自动分配到 【DB_" + dbRouter +"】数据源处理数据");
        DynamicDataSourceEntity.set(dbRouter);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.befor(args[0]);
        Object obj = method.invoke(proxyObj, args);
        this.after();
        return obj;
    }
}
