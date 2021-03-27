package com.demo.pattern.decorator.battercake.v1;

public class BattercakeWithEggAndSauage extends BattercakeWithEgg {
    @Override
    public String getMsg(){
        return super.getMsg() + "加香肠";
    }

    @Override
    public int getPrice(){
        return super.getPrice() + 5;
    }
}
