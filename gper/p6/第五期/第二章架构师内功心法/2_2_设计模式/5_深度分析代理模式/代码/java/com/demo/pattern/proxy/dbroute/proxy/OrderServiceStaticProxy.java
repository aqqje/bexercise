package com.demo.pattern.proxy.dbroute.proxy;

import com.demo.pattern.proxy.dbroute.IOrderService;
import com.demo.pattern.proxy.dbroute.Order;
import com.demo.pattern.proxy.dbroute.OrderService;
import com.demo.pattern.proxy.dbroute.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderServiceStaticProxy {

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    private IOrderService orderService;

    public OrderServiceStaticProxy(){
        orderService = new OrderService();
    }

    public int createOrder(Order order) {
        System.out.println("OrderService调用orderDao创建订单");
        long time = order.getCreateTime();
        Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
        System.out.println("静态代理类自动分配到 【DB_" + dbRouter +"】数据源处理数据");
        DynamicDataSourceEntity.set(dbRouter);
        int rows = orderService.createOrder(order);
        DynamicDataSourceEntity.restore();
        return rows;
    }
}
