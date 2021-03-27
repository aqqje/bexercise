package com.demo.patter.flyweigth.ticket;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketFacotry {

    private static Map<String, ITicket> pool = new ConcurrentHashMap<>();

    public static ITicket queryTicket(String from, String to){
        String key = from + "->" + to;
        if(pool.containsKey(key)){
            System.out.println("使用缓存");
            return pool.get(key);
        }
        System.out.println("首次查询，创建对象：" + key);
        ITicket ticket = new TrainTicket(from, to);
        pool.put(key, ticket);
        return ticket;
    }
}
