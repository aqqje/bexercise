package com.demo.pattern.state.order;

import lombok.Data;

@Data
public class Order {

    private int id;
    private OrderStatus state;
}
