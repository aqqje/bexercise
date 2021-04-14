package com.demo.pattern.mediator.rpc;

public class CService implements Iservice{
    Registy registy;
    public CService(){
        registy.register("cService", this);
    }

    public void a(){
        //registy.get("bService").xxx();
    }
}
