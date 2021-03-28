package com.demo.pattern.state.order;

import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

@Component("orderStateListener")
@WithStateMachine(name = "orderStateMachine")
public class OrderStateListenerImpl {

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public boolean payTransitim(Message<OrderStatusChangeEven> message){
        Order order = (Order) message.getHeaders().get("order");
        order.setState(OrderStatus.WAIT_DELIVER);
        System.out.println("支付, 状态机反馈信息: " + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransitim(Message<OrderStatusChangeEven> message){
        Order order = (Order) message.getHeaders().get("order");
        order.setState(OrderStatus.WAIT_DELIVER);
        System.out.println("发货 , 状态机反馈信息: " + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "WAIT_FINISH")
    public boolean receiveTransitim(Message<OrderStatusChangeEven> message){
        Order order = (Order) message.getHeaders().get("order");
        order.setState(OrderStatus.WAIT_FINISH);
        System.out.println("收货 , 状态机反馈信息: " + message.getHeaders().toString());
        return true;
    }

}
