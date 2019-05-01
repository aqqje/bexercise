package cn.transaction.proxy.test1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 一个经纪公司的要求:
 * 能做基本的表演和危险的表演
 */
public interface IActor {
    void basicActor(double money);

    String dangerActor(double money);

    /**
     * 实现了接口，就表示具有接口中的方法实现。即：符合经纪公司的要求
     */
    class Actor implements IActor{

        @Override
        public void basicActor(double money) {
            System.out.println("basicActor演出费:" + money );
        }

        @Override
        public String dangerActor(double money) {
            System.out.println("dangerActor演出费:" + money );
            return "aqqje";
        }
    }

    // 一个剧情
    public static void main(String[] args) {
        /**
         * 代理：
         * 间接。
         * 获取代理对象：
         * 要求：
         * 被代理类最少实现一个接口
         * 创建的方式
         * Proxy.newProxyInstance(三个参数)
         * 参数含义：
         * ClassLoader：和被代理对象使用相同的类加载器。
         * Interfaces：和被代理对象具有相同的行为。实现相同的接口。
         * InvocationHandler：如何代理。
         * 策略模式：使用场景是：
         * 数据有了，目的明确。
         * 如何达成目标，就是策略。
         *
         */
        IActor actor = (IActor) Proxy.newProxyInstance(Actor.class.getClassLoader(), Actor.class.getInterfaces(), new InvocationHandler() {
            /**
             * 执行被代理对象的任何方法，都会经过该方法。
             * 此方法有拦截的功能。
             *
             * 参数：
             * proxy：代理对象的引用。不一定每次都用得到
             * method：当前执行的方法对象
             * args：执行方法所需的参数
             * 返回值：
             * 当前执行方法的返回值
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object obj = null;
                Double money = (Double) args[0];
                String methodName = method.getName();
                if ("basicActor".equals(methodName)) {
                    obj = method.invoke(Actor.class.newInstance(), money + 111);
                } else {
                    obj = method.invoke(Actor.class.newInstance(), money + 222);
                }
                return obj;
            }
        });

        actor.basicActor(100);
        System.out.println(actor.dangerActor(100));;
    }
}
