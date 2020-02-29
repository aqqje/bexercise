package vip.designpattern.singleton;

import vip.designpattern.singleton.register.EnumSingleton;

/**
 * Create by aqqje on 2020/2/29.
 */
public class EnumSingletonTest {
    public static void main(String[] args) {
        EnumSingleton instance = EnumSingleton.getInstance();
        instance.setData(new Object());
    }
}
