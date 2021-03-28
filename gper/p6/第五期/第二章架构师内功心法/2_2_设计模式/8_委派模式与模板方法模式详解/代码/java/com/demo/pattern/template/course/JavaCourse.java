package com.demo.pattern.template.course;

public class JavaCourse extends AbstractCourse {

    private boolean needCheckHomework = false;

    @Override
    protected void checkHomework() {
        System.out.println("检查java作业");
    }

    @Override
    protected boolean needCheckHomework(){
        return needCheckHomework;
    }

    public void setNeedCheckHomework(boolean needCheckHomework) {
        this.needCheckHomework = needCheckHomework;
    }
}
