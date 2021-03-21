package com.demo.pattern.proxy.dynamicproxy.gpproxy.proxy;


public class GPPorxy {
    public static  Object newProxyInstance(GPClassLoader loader,
                                           Class<?>[] interfaces,
                                           GPInvocationHandler h ){

        // 1, 动态生成源码.java文件

        // 2, Java文件输出到磁盘，保存为文件$Proxy0.java

        // 3, 把.java文件编译成$Proxy0.class文件

        // 4, 加载到内存

        // 5,返回新的代理对象
        return null;

    }

    private static String generateSrc(Class<?> interfaces){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("");
        return sb.toString();
    }
}

