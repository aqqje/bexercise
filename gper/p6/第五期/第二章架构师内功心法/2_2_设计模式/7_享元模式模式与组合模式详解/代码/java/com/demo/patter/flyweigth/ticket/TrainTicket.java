package com.demo.patter.flyweigth.ticket;

import lombok.Data;

import java.util.Random;

@Data
public class TrainTicket implements ITicket {

    private String from;
    private String to;
    private int price;

    public TrainTicket(String from, String to) {
        this.from = from;
        this.to = to;
    }


    @Override
    public void showInfo(String bunk) {
        this.price = new Random().nextInt(50000);
        System.out.println(from + "->" + to + ":" + bunk + "价格：" + price);
    }
}
