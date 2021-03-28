package com.demo.pattern.state.order;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.HashMap;
import java.util.Map;
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private StateMachine<OrderStatus, OrderStatusChangeEven> orderStateMachine;

    @Autowired
    private StateMachinePersister<OrderStatus, OrderStatusChangeEven, Order> persister;

    private int id = 1;

    private Map<Integer, Order> orders = new HashMap<Integer, Order>();

    @Override
    public Map<Integer, Order> getOrders() {
        return orders;
    }

    @Override
    public Order create(){
        Order order = new Order();
        order.setState(OrderStatus.WAIT_FINISH);
        order.setId(id++);
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Order pay(int id){
        Order order = orders.get(id);
        System.out.println("线程名称: " + Thread.currentThread().getName() + " 尝试支付, 订单号: " + id);
        Message<OrderStatusChangeEven> message = MessageBuilder.withPayload(OrderStatusChangeEven.PAYED).setHeader("order", order).build();
        if(!sendEvent(message, order)){
            System.out.println("线程名称: " + Thread.currentThread().getName() + " 支付失败, 状态异常, 订单号: " + id);
        }
        return order;
    }

    @Override
    public Order deliver(int id){
        Order order = orders.get(id);
        System.out.println("线程名称: " + Thread.currentThread().getName() + " 尝试发货, 订单号: " + id);
        Message<OrderStatusChangeEven> message = MessageBuilder.withPayload(OrderStatusChangeEven.PAYED).setHeader("order", order).build();
        if(!sendEvent(message, order)){
            System.out.println("线程名称: " + Thread.currentThread().getName() + " 发货失败, 状态异常, 订单号: " + id);
        }
        return order;
    }

    @Override
    public Order receive(int id){
        Order order = orders.get(id);
        System.out.println("线程名称: " + Thread.currentThread().getName() + " 尝试收货, 订单号: " + id);
        Message<OrderStatusChangeEven> message = MessageBuilder.withPayload(OrderStatusChangeEven.PAYED).setHeader("order", order).build();
        if(!sendEvent(message, order)){
            System.out.println("线程名称: " + Thread.currentThread().getName() + " 收货失败, 状态异常, 订单号: " + id);
        }
        return order;
    }

    private synchronized boolean sendEvent(Message<OrderStatusChangeEven> message, Order order){
        boolean result = false;
        try {
            orderStateMachine.start();
            // 尝试居处状态机状态
            persister.restore(orderStateMachine, order);
            // 添加延迟用于线程安全测试
            Thread.sleep(1000);
            result = orderStateMachine.sendEvent(message);
            // 持久化状态机状态
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            orderStateMachine.stop();
        }
        return result;
    }

}
