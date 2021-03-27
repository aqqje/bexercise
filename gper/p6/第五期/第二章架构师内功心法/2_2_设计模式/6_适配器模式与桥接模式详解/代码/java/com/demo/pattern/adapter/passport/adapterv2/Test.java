package com.demo.pattern.adapter.passport.adapterv2;

public class Test {
    public static void main(String[] args) {
        IPassportForThird adapter = new PassportForThirdAdapter();
        adapter.loginForQQ("dsfsfsdffsafasf");
    }
}
