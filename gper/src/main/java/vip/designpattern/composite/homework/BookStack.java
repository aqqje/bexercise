package vip.designpattern.composite.homework;

public abstract class BookStack {

    protected String name;

    public BookStack(String name){this.name = name;}

    public abstract void show();
}
