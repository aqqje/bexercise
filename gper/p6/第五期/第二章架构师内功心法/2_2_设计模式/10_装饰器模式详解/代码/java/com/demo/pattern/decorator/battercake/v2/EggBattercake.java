package com.demo.pattern.decorator.battercake.v2;

public class EggBattercake extends BattercakeDecorator {

    public EggBattercake(BaseBattercake battercake) {
        super(battercake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "加鸡蛋";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 1;
    }
}
