package com.demo.pattern.proxy.dynamicproxy.cglibproxy;

public class Test {
    public static void main(String[] args) {
        CglibMeipo cglibMeipo = new CglibMeipo();
        Zhangsan zhangsan = (Zhangsan)cglibMeipo.getInstance(Zhangsan.class);
        zhangsan.findLove();
    }
}
