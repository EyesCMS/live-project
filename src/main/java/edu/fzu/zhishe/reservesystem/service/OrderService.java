package edu.fzu.zhishe.reservesystem.service;

import edu.fzu.zhishe.reservesystem.generator.OrderList;

public interface OrderService {

    OrderList findById(Integer id);

    int add(OrderList order);

    boolean hitJack(Integer id);
}
