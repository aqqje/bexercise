package com.demo.patter.composite.safe;

public abstract class Direcotry {

    protected String name;

    public abstract void show();

    public Direcotry(String name) {
        this.name = name;
    }
}
