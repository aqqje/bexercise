package com.demo.pattern.prototype.simple;

import lombok.Data;

import java.util.List;

@Data
public class ConcretePrototypeA implements IPrototype<ConcretePrototypeA> {

    private int age;
    private String name;
    private List hobbies;

    /**
     * 浅克隆
     * @return
     */
    @Override
    public ConcretePrototypeA clone() {
        ConcretePrototypeA ConcretePrototype = new ConcretePrototypeA();
        ConcretePrototype.setAge(this.age);
        ConcretePrototype.setName(this.name);
        ConcretePrototype.setHobbies(this.hobbies);
        return ConcretePrototype;
    }
}
