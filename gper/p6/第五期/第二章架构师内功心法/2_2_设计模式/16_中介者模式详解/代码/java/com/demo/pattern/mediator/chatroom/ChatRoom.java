package com.demo.pattern.mediator.chatroom;

public class ChatRoom {

    public  void showMsg(User user, String msg){
        System.out.println("[" + user.getUsername() +"] : " + msg );
    }
}
