package com.demo.pattern.iterator.course;

import lombok.Data;

@Data
public class Course {

    private String name;

    public Course(String name) {
        this.name = name;
    }
}
