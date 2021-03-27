package com.demo.pattern.decorator.battercake.v1;

public class BattercakeWithEgg extends Battercake {
    @Override
    public String getMsg(){
        return super.getMsg() + "加鸡蛋";
    }

    @Override
    public int getPrice(){
        return super.getPrice() + 2;
    }
}
