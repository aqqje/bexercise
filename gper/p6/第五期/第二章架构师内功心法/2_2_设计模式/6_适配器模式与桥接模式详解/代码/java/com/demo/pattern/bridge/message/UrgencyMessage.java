package com.demo.pattern.bridge.message;

public class UrgencyMessage extends AbstractMessage {
    UrgencyMessage(IMessage message) {
        super(message);
    }

    @Override
    public void sendMessage(String message, String toUser) {
        message = "[加急]" + message;
        super.sendMessage(message, toUser);
    }

}
