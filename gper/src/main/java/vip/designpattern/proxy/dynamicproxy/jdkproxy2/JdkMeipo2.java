package vip.designpattern.proxy.dynamicproxy.jdkproxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkMeipo2 implements InvocationHandler {

    /** 代理目标对象 */
    private IPerson target;

    public IPerson getInstance(IPerson person){
        this.target = person;
        Class<?> clzz = person.getClass();
        return (IPerson) Proxy.newProxyInstance(clzz.getClassLoader(), clzz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(this.target, args);
        after();
        return invoke;
    }

    private void after() {
        System.out.println("------------->  after");
    }

    private void before() {
        System.out.println("*************>  before");
    }
}
