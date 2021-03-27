package com.demo.pattern.bridge.message;

public class EamilMessage implements IMessage {
    @Override
    public void send(String message, String toUser) {
        System.out.println("使用邮件发送信息" + message + "给" + toUser);
    }
}
