package edu.fzu.zhishe.reservesystem.service;

import edu.fzu.zhishe.reservesystem.generator.Order;

public interface OrderService {

    void add(Order order);

    void queryOrderResult();
}
