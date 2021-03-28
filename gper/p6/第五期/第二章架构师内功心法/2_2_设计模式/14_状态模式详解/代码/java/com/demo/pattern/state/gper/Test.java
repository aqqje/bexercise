package com.demo.pattern.state.gper;

public class Test {

    public static void main(String[] args) {
        AppContext context = new AppContext();
        context.favorite();
        context.comment("评论:好文章, 666个赞");
    }
}
