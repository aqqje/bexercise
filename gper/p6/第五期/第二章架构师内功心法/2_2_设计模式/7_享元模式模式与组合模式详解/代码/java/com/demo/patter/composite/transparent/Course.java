package com.demo.patter.composite.transparent;

import lombok.Data;

@Data
public class Course extends CourseComponet {

    private String name;
    private int price;

    public Course(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName(CourseComponet componet) {
        return this.getName(componet);
    }

    @Override
    public double getPrice(CourseComponet componet) {
        return this.getPrice(componet);
    }

    @Override
    public void print() {
        System.out.println(this.name + " (￥" + this.price + "元）");
    }
}
