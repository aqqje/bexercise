package com.demo.patter.composite.safe;

import com.demo.patter.composite.transparent.CourseComponet;

import java.util.ArrayList;
import java.util.List;

public class Folder extends Direcotry {

    private List<Direcotry> dirs;
    private int level;

    public Folder(String name, int level) {
        super(name);
        this.level = level;
        this.dirs = new ArrayList<>();
    }

    @Override
    public void show() {
        System.out.println(this.name);
        for (Direcotry item : dirs) {
            for (Integer i = 0; i < this.level; i++) {
                System.out.print("\t");
            }
            for (Integer i = 0; i < this.level; i++) {
                if(i == 0){
                    System.out.print("+");
                }
                System.out.print("-");
            }
            item.show();
        }
    }

    public boolean add(Direcotry dir){
        return this.dirs.add(dir);
    }

    public Direcotry get(int index){
        return this.dirs.get(index);
    }

    public void list(){
        for (Direcotry dir : dirs) {
            System.out.println(dir.name);
        }
    }
}
