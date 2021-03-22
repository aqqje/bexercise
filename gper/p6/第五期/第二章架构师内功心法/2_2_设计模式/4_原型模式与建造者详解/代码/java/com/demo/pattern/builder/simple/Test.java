package com.demo.pattern.builder.simple;


public class Test {

    public static void main(String[] args) {
        CourseBuilder builder = new CourseBuilder();
        builder.addHomework("homework").addVideo("video").addPpt("ppt").addName("name");
        Course course = builder.CourseBuilder();
        System.out.println(course);
    }
}
