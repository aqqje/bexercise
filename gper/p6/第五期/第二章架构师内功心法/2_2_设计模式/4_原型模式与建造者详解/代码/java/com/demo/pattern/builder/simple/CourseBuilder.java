package com.demo.pattern.builder.simple;

/**
 * Course 建造器
 */
public class CourseBuilder {

    private Course course = new Course();

    public CourseBuilder addName(String name) {
        course.setName(name);
        return this;
    }
    public CourseBuilder addPpt(String ppt){
        course.setPpt(ppt);
        return this;
    }
    public CourseBuilder addVideo(String video){
        course.setVideo(video);
        return this;
    }
    public CourseBuilder addHomework(String homework){
        course.setHomework(homework);
        return this;
    }

    public Course CourseBuilder(){
        return course;
    }
}
