package com.demo.pattern.command.player;

public class GPlayer {

    public void play(){
        System.out.println("正常播放");
    }

    public void spedd(){
        System.out.println("拖动进度条");
    }

    public void stop(){
        System.out.println("停止播放");
    }

    public void pause(){
        System.out.println("暂停播放");
    }
}
