package com.demo.pattern.state.order;

/**
 * 订单状态改变事件
 */
public enum  OrderStatusChangeEven {

    // 支付, 发货, 确认收货
    PAYED, DELIVERY, RECEIVED;
}
