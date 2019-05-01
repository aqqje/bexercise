package cn.transaction.proxy.test1;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Actor {

    public void basicActor(double money) {
        System.out.println("basicActor演出费:" + money );
    }

    public String dangerActor(double money) {
        System.out.println("dangerActor演出费:" + money );
        return "aqqje11111";
    }

    public static void main(String[] args) {
        /**
         * 基于子类的动态代理
         * 要求：
         * 被代理对象不能是最终类
         * 用到的类：
         * Enhancer
         * 用到的方法：
         * create(Class, Callback)
         * 方法的参数：
         * Class：被代理对象的字节码
         * Callback：如何代理
         * @param args
         */
        Actor actor = (Actor)Enhancer.create(Actor.class, new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法，都会经过该方法。在此方法内部就可以对被代理对象的任何方法进行增强。
             * 参数：
             * 前三个和基于接口的动态代理是一样的。
             * MethodProxy：当前执行方法的代理对象。
             * 返回值：
             * 当前执行方法的返回值
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object obj = null;
                String methodName = method.getName();
                double money = (double) objects[0];
                if ("dangerActor".equals(methodName)) {
                    obj = method.invoke(Actor.class.newInstance(), money + 500);
                } else {
                    obj = method.invoke(Actor.class.newInstance(), money + 100);
                }
                return obj;
            }
        });

        actor.basicActor(100);
        System.out.println(actor.dangerActor(100));;
    }

}
