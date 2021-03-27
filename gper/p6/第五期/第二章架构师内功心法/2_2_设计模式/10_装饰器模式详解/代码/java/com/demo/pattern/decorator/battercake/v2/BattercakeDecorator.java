package com.demo.pattern.decorator.battercake.v2;

public class BattercakeDecorator extends BaseBattercake {

    private Battercake battercake;

    public BattercakeDecorator(BaseBattercake battercake){
        this.battercake = battercake;
    }
    @Override
    protected String getMsg(){
        return this.battercake.getMsg();
    }

    @Override
    protected int getPrice(){
        return this.battercake.getPrice();
    }
}
