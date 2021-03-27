package com.demo.pattern.decorator.battercake.v2;

public class SauageBattercake extends BattercakeDecorator {

    public SauageBattercake(BaseBattercake battercake) {
        super(battercake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "加香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 3;
    }
}
