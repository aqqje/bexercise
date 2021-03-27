package com.demo.pattern.adapter.passport.adapterv1;

import com.demo.pattern.adapter.passport.adapterv1.PassportForThirdAdapter;

public class Test {
    public static void main(String[] args) {
        PassportForThirdAdapter adapter = new PassportForThirdAdapter();
        adapter.loginForQQ("asdfadffasfasdfafasf");
        adapter.loginForWechar("ehrerteewddsfdszf");
        adapter.login("aqqje", "123466778");
    }
}
