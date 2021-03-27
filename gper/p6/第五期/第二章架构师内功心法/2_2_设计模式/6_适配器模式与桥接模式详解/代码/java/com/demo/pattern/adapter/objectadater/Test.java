package com.demo.pattern.adapter.objectadater;

public class Test {
    public static void main(String[] args) {
        PowerAdapter powerAdapter = new PowerAdapter(new AC220());
        powerAdapter.output5v();
    }
}
