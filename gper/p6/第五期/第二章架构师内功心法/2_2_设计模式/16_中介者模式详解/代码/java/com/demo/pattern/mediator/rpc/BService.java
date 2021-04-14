package com.demo.pattern.mediator.rpc;

public class BService implements Iservice{
    Registy registy;
    public BService(){
        registy.register("bService", this);
    }
}
