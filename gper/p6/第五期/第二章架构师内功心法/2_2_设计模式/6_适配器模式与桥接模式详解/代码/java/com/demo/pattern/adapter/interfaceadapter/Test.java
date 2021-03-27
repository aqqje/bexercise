package com.demo.pattern.adapter.interfaceadapter;


public class Test {
    public static void main(String[] args) {
        DC dc = new PowerAdapter(new AC220());
        dc.output5V();

    }
}
