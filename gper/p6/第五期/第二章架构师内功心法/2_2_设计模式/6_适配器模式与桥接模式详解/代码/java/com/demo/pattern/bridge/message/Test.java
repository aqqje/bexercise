package com.demo.pattern.bridge.message;

public class Test {
    public static void main(String[] args) {
        SmsMessage smsMessage = new SmsMessage();

        AbstractMessage nomalMessage = new NomalMessage(smsMessage);
        nomalMessage.sendMessage("加班", "aqqje");

        EamilMessage eamilMessage = new EamilMessage();
        AbstractMessage urgencyMessage = new UrgencyMessage(eamilMessage);
        urgencyMessage.sendMessage("放假", "aqqje");
    }
}
