package com.demo.pattern.mediator.rpc;

public class AService implements Iservice{
    Registy registy;
    public AService(){
        registy.register("aService", this);
    }
}
