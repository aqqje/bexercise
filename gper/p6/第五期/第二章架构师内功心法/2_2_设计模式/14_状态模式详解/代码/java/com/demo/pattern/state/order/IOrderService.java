package com.demo.pattern.state.order;

import java.util.Map;

public interface IOrderService {

    Order create();

    Order pay(int id);

    Order deliver(int id);

    Order receive(int id);

    Map<Integer, Order> getOrders();
}
