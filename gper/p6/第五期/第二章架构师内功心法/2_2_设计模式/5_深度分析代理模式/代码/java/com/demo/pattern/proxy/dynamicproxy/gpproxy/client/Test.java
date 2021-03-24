package com.demo.pattern.proxy.dynamicproxy.gpproxy.client;

import com.demo.pattern.proxy.dynamicproxy.gpproxy.proxy.GPMeipo;



public class Test {

    public static void main(String[] args) throws Exception {
        GPMeipo gpMeipo = new GPMeipo();
        IPerson zhangsan = gpMeipo.getInstance(new Zhangsan());
        zhangsan.findLove();
        zhangsan.buyInsure();

//        // 输出代理对象
//        byte[] $Proxy0s = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{IPerson.class});
//        FileOutputStream fos = new FileOutputStream("/Users/aqqje/AqqJe/$Proxy0.class");
//        fos.write($Proxy0s);
//
//        IPerson zhaoLiu = jdkMeipo.getInstance(new ZhaoLiu());
//        zhaoLiu.findLove();
//        zhaoLiu.buyInsure();
    }
}
