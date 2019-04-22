package cn.aqqje.dome.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PoxryTest{

    public static void main(String[] args) {
        Lenove lenove = new Lenove();

        SaleComputer proxy = (SaleComputer)Proxy.newProxyInstance(Lenove.class.getClassLoader(), Lenove.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().contains("sale")){
                    int menoy = (int)args[0]* 2;// 参数增强
                    System.out.println("送人"); /*方法前增强*/
                    Object obj = method.invoke(lenove, menoy);
                    System.out.println("送货"); /*方法后增强*/
                    return obj + "_aqqje"; // 结果增强
                }else{
                    Object obj = method.invoke(lenove, args);
                    return obj;
                }
            }
        });

        String sale = proxy.sale(500);
        System.out.println(sale);
    }
}
