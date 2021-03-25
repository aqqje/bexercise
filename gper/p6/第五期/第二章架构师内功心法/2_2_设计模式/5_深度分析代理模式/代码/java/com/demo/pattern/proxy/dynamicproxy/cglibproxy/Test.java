package com.demo.pattern.proxy.dynamicproxy.cglibproxy;


import org.springframework.cglib.core.DebuggingClassWriter;

public class Test {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/aqqje/AqqJe/");
        CglibMeipo cglibMeipo = new CglibMeipo();
        Zhangsan zhangsan = (Zhangsan)cglibMeipo.getInstance(Zhangsan.class);
        zhangsan.findLove();
    }
}
