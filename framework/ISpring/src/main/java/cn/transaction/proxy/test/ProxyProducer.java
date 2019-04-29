package cn.transaction.proxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyProducer implements InvocationHandler {

    private Producer producer;

    public ProxyProducer(Producer producer){
        this.producer = producer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /**
         * 作用：执行被代理对象的任何接口方法都会经过该方法
         * 方法参数的含义
         * @param proxy   代理对象的引用
         * @param method  当前执行的方法
         * @param args    当前执行方法所需的参数
         * @return        和被代理对象方法有相同的返回值
         * @throws Throwable
         */
        Object obj = null;
        Float money = (Float) args[0];
        if("saleProduct".equals(method.getName())){
            obj = method.invoke(producer ,money * 0.8f);
        }else{
            obj = method.invoke(producer,money * 0.5f);
        }
        return obj;
    }
}
