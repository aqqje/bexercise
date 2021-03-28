package com.demo.pattern.template.course;

public class PythonCourse extends AbstractCourse {
    @Override
    protected void checkHomework() {
        System.out.println("检查python作业");
    }
}
