package edu.fzu.zhishe.reservesystem.service;

import edu.fzu.zhishe.reservesystem.generator.OrderList;

public interface OrderService {

    void add(OrderList order);

    void queryOrderResult();
}
