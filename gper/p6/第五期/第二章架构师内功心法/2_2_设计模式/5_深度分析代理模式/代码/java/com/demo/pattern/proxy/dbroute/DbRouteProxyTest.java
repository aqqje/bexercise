package com.demo.pattern.proxy.dbroute;

import com.demo.pattern.proxy.dbroute.db.DynamicDataSourceEntity;
import com.demo.pattern.proxy.dbroute.proxy.OrderServiceStaticProxy;
import com.demo.pattern.proxy.dbroute.proxy.OrderServicedynamicProxy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbRouteProxyTest {

    public static void main(String[] args) throws ParseException {
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = sdf.parse("2021/03/01");

        IOrderService orderService = new OrderService();
        orderService.createOrder(new Order());*/

        // new OrderServiceStaticProxy().createOrder(new Order());
        IOrderService orderService = (IOrderService) new OrderServicedynamicProxy().getInstance(new OrderService());
        orderService.createOrder(new Order());

    }
}
