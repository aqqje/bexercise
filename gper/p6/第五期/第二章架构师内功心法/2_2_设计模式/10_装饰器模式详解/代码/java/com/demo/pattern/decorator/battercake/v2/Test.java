package com.demo.pattern.decorator.battercake.v2;

public class Test  {
    public static void main(String[] args) {
        BaseBattercake baseBattercake = new BaseBattercake();

        baseBattercake = new EggBattercake(baseBattercake);

        baseBattercake = new SauageBattercake(baseBattercake);
        System.out.println(baseBattercake.getMsg() + ", 总价:" + baseBattercake.getPrice());
    }
}
