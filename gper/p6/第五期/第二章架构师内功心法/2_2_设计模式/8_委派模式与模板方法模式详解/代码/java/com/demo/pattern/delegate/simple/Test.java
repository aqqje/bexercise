package com.demo.pattern.delegate.simple;

public class Test {
    public static void main(String[] args) {
        Boss boss = new Boss();
        Leader leader = new Leader();
        boss.command("编程", leader);
        boss.command("平面设计", leader);
    }
}
