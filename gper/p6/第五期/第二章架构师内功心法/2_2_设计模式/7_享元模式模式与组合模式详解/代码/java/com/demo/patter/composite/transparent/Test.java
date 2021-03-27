package com.demo.patter.composite.transparent;

public class Test {

    public static void main(String[] args) {
        System.out.println("=============透明的组合模式==============");
        CourseComponet javaBase = new Course("Java入门课程",8280);
        CourseComponet ai = new Course("人工智能",5000);

        // ai.addChild(javaBase); | 不支持添加操作

        CourseComponet coursePackage = new CoursePackage("Java架构师课程",2);
        CourseComponet design = new Course("Java设计模式",1500);
        CourseComponet source = new Course("源码分析",2000);
        CourseComponet softSkill = new Course("软技能",3000);

        coursePackage.addChild(design);
        coursePackage.addChild(source);
        coursePackage.addChild(softSkill);

        CourseComponet catalog = new CoursePackage("咕泡课程目录",1);
        catalog.addChild(javaBase);
        catalog.addChild(ai);
        catalog.addChild(coursePackage);

        catalog.print();
    }
}
