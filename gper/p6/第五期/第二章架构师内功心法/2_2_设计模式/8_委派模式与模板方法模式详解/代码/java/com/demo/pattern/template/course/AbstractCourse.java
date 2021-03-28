package com.demo.pattern.template.course;

public abstract class AbstractCourse {

    protected final void createCourese(){
        // 1, 发布预习资源
        postPreResouces();
        // 2, 制作课件
        createPPT();
        // 3, 直播授课
        liveVideo();
        // 4, 上传课后资料
        postResource();
        // 5, 布置作业
        postHomework();
    }

    protected void postHomework(){
        System.out.println("布置作业");
    }

    protected void postResource(){
        System.out.println("上传课后资料");
    }

    protected void liveVideo(){
        System.out.println("直播授课");
    }

    protected void createPPT(){
        System.out.println("制作课件");
    }

    protected void postPreResouces(){
        System.out.println("发布预习资源");
    }


    protected abstract void checkHomework();

    // 钩子方法
    protected boolean needCheckHomework(){
        return false;
    }
}
