package com.demo.pattern.state.gper;

import lombok.Data;

@Data
public class LoginState extends UserState{
    @Override
    public void favorite() {
        System.out.println("收藏成功");
    }

    @Override
    public void comment(String comment) {
        System.out.println("评论成功");
    }



}
