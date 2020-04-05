package vip.designpattern.composite.homework;

public class Book extends BookStack{

    public Book(String name) {super(name);}

    @Override
    public void show() {
        System.out.println(this.name);
    }
}
