package com.demo.pattern.mediator.chatroom;

import lombok.Data;

@Data
public class User {
    private String username;

    private ChatRoom chatRoom;

    public User(String username, ChatRoom chatRoom) {
        this.username = username;
        this.chatRoom = chatRoom;
    }

    public void sendMassage(String msg) {
        this.chatRoom.showMsg(this, msg);
    }
}
