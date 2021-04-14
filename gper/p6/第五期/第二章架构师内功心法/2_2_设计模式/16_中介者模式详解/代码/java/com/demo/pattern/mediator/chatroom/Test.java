package com.demo.pattern.mediator.chatroom;

public class Test {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();
        User tom = new User("tom", chatRoom);
        User jerry = new User("jerry", chatRoom);

        tom.sendMassage("Hi! I am Tom ");
        jerry.sendMassage("HELLO! MY Jerry ");
    }
}
