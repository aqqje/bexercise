package com.demo.patter.composite.safe;

import com.demo.patter.composite.transparent.CourseComponet;

import java.util.ArrayList;
import java.util.List;

public class File extends Direcotry {

    public File(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println(this.name);

    }


}
