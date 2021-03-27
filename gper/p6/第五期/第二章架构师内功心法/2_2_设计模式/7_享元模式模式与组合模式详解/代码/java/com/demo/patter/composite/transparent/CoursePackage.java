package com.demo.patter.composite.transparent;

import java.util.ArrayList;
import java.util.List;

public class CoursePackage extends CourseComponet {

    private List<CourseComponet> items = new ArrayList<CourseComponet>();

    private String name;
    private Integer level;

    public CoursePackage(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public String getName(CourseComponet componet) {
        return this.name;
    }

    @Override
    public void addChild(CourseComponet componet) {
        items.add(componet);
    }

    @Override
    public void removeChild(CourseComponet componet) {
        items.remove(componet);
    }

    @Override
    public void print() {
        System.out.println(this.name);
        for (CourseComponet item : items) {
            for (Integer i = 0; i < this.level; i++) {
                System.out.print("\t");
            }
            for (Integer i = 0; i < this.level; i++) {
                if(i == 0){
                    System.out.print("+");
                }
                System.out.print("-");
            }
            item.print();
        }
    }
}
