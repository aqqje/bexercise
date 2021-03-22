package com.demo.pattern.proxy.dynamicproxy.jdkproxy;

public class ZhaoLiu implements IPerson {
    @Override
    public void findLove() {
        System.out.println("赵六要求：肤白貌美大长腿");
    }

    @Override
    public void buyInsure() {
        System.out.println("610");
    }
}
