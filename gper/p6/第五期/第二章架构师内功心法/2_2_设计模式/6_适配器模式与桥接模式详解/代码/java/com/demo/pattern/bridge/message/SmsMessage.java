package com.demo.pattern.bridge.message;

public class SmsMessage implements IMessage {
    @Override
    public void send(String message, String toUser) {
        System.out.println("使用短信发送信息" + message + "给" + toUser);
    }
}
