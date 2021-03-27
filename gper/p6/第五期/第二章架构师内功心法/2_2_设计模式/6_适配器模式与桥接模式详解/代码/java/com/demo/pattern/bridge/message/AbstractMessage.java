package com.demo.pattern.bridge.message;

public abstract class AbstractMessage{
    private IMessage message;

    AbstractMessage(IMessage message){
        this.message = message;
    }

    void sendMessage(String message, String toUser) {
        this.message.send(message, toUser);
    }
}
