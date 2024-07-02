package com.wstutorial.ws;

import com.wstutorial.ws.commonservice.AcknowledgementCode;
import com.wstutorial.ws.orderservice.*;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    List<Order> orders = new ArrayList<>();

    public OrderRepository() {
        Order order1 = new Order();
        order1.setId(1001);
        order1.setAmount(1320);
        order1.setDatetime(20240601);
        order1.setType(OrderType.CUSTOMER);
        order1.setStatus(OrderStatus.PENDING);
        order1.setCustomerOrRetailerId(1001);


        Order order2 = new Order();
        order2.setId(1002);
        order2.setAmount(3680);
        order2.setDatetime(20240602);
        order2.setType(OrderType.RETAILER);
        order2.setStatus(OrderStatus.CONFIRMED);
        order2.setCustomerOrRetailerId(1001);

        orders.add(order1);
        orders.add(order2);
    }
    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(Long id) {
        for(Order r: orders) {
            if(r.getId() == id) {
                return r;
            }
        }
        System.out.println("Order not found with id: " + id);
        return null;
    }

    public AcknowledgementCode cancelOrderById(Long id) {
        for(Order r: orders) {
            if(r.getId() == id) {
                orders.remove(r);
                return AcknowledgementCode.DELETED;
            }
        }
        System.out.println("Order not found with id: " + id + ", Delete aborted");
        return AcknowledgementCode.FAILED;
    }

    public AcknowledgementCode updateOrderById(Long id, Order order) {
        if (id == null || order == null) {
            System.out.println("Invalid input: ID or order is null");
            return AcknowledgementCode.FAILED;
        }

        for (int i = 0; i < orders.size(); i++) {
            Long orderId = orders.get(i).getId();
            if (orderId.equals(id)) {
                orders.set(i, order);
                return AcknowledgementCode.UPDATED;
            }
        }
        System.out.println("Order not found with id: " + id + ", Update aborted");
        return AcknowledgementCode.FAILED;
    }


    public void createNewOrder(Order order) {
        order.setId(orders.size()+1001);
        orders.add(order);
    }

}
