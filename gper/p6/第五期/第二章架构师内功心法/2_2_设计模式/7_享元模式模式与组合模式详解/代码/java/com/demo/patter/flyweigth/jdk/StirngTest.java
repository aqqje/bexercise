package com.demo.patter.flyweigth.jdk;

public class StirngTest {

    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = "he" + "llo";
        String s4 = "hel" + new String("lo");
        String s5 = new String("hello");
        String s6 = s5.intern(); // 获取s5的常量地址
        String s7 = "h";
        String s8 = "hello";
        String s9 = s7 + s8;
        //System.out.println(s1 == s2); // true
        //System.out.println(s1 == s3); // true
        //System.out.println(s1 == s4); // false
        //System.out.println(s1 == s5); // false
        //System.out.println(s4 == s5); // false
        System.out.println(s1 == s6); // true
        System.out.println(s1 == s9); // false
    }
}
