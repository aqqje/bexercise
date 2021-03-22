package com.demo.pattern.prototype.simple;

import java.util.ArrayList;

/**
 * 克隆模式测试
 */
public class PrototypeTest {

    public static void main(String[] args) {
        ConcretePrototypeA ConcretePrototype = new ConcretePrototypeA();
        ConcretePrototype.setAge(18);
        ConcretePrototype.setName("AqqJE");
        ConcretePrototype.setHobbies(new ArrayList());


        Client client = new Client();

        ConcretePrototypeA copy = (ConcretePrototypeA) client.startClone(ConcretePrototype);

        System.out.println(ConcretePrototype);
        System.out.println(copy.toString());

        System.out.println(ConcretePrototype.getHobbies() == copy.getHobbies());

    }
}
