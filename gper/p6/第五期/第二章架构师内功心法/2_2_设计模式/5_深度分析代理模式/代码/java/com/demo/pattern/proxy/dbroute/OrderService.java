package com.demo.pattern.proxy.dbroute;

import com.demo.pattern.proxy.dbroute.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderService implements IOrderService {

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    private OrderDao orderDao;

    public OrderService(){
        orderDao = new OrderDao();
    }

    public int createOrder(Order order) {
        /*System.out.println("OrderService调用orderDao创建订单");
        long time = order.getCreateTime();
        Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
        System.out.println("自动切换数据源到 【" + dbRouter +"】");
        DynamicDataSourceEntity.set(dbRouter);*/
        int rows = orderDao.insert(order);
        /*DynamicDataSourceEntity.restore();*/
        return rows;
    }
}
