package com.demo.patter.flyweigth.jdk;

public class IntergerTest {

    public static void main(String[] args) {
        Integer a = Integer.valueOf(100);
        Integer b = 100;

        Integer c = Integer.valueOf(1000);
        Integer d = 1000;

        System.out.println(a == b); // true
        System.out.println(c == d); // false  |  缓存只到 -128 ~ 127

        Long a1 = Long.valueOf(100);
        Long b1 = 100L;

        Long c1 = Long.valueOf(1000);
        Long d1 = 1000L;

        int a2 = 129;
        Integer b2 = Integer.valueOf(129);

        System.out.println(a1 == b1); // true
        System.out.println(c1 == d1); // false | 缓存只到 -128 ~ 127

        System.out.println(a2 == b2); // true | b2 自动拆箱为 int， 这里为值比较
    }
}
