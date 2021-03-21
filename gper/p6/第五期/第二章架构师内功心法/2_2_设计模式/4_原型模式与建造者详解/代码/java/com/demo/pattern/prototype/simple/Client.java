package com.demo.pattern.prototype.simple;

public class Client {

    private IPrototype<ConcretePrototypeA> prototype;

    public IPrototype startClone(IPrototype<ConcretePrototypeA> concretePrototype){
        return concretePrototype.clone();
    }
}
