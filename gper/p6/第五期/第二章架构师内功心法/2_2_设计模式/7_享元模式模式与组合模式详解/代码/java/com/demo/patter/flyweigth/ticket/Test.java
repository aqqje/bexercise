package com.demo.patter.flyweigth.ticket;

public class Test {

    public static void main(String[] args) {
        ITicket ticket1 = TicketFacotry.queryTicket("长沙", "北京");
        ticket1.showInfo("硬座");

        ITicket ticket2 = TicketFacotry.queryTicket("长沙", "北京");
        ticket2.showInfo("软座");
    }
}
