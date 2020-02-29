package vip.designpattern.singleton;

import vip.designpattern.singleton.register.ContainerSingleton;

/**
 * Create by aqqje on 2020/2/29.
 */
public class ContainerSingletonTest {
    public static void main(String[] args) {
        Object instance = ContainerSingleton.getInstance("vip.designpattern.singleton.Pojo");
        System.out.println(instance);
    }
}
