package com.demo.pattern.template.course;

public class Test {
    public static void main(String[] args) {
        JavaCourse java = new JavaCourse();
        java.createCourese();
        java.setNeedCheckHomework(true);

        System.out.println("==================");

        PythonCourse python = new PythonCourse();
        python.createCourese();

    }
}
