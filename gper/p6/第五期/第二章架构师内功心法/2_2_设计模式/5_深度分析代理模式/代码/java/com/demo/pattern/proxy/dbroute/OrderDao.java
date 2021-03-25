package com.demo.pattern.proxy.dbroute;

public class OrderDao {
    public int insert(Order order) {
        System.out.println(" OrderDao 订单创建成功 ！");
        return 1;
    }
}
