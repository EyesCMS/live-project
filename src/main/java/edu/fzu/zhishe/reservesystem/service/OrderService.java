package edu.fzu.zhishe.reservesystem.service;

import edu.fzu.zhishe.reservesystem.generator.OrderList;
import edu.fzu.zhishe.reservesystem.generator.Task;

public interface OrderService {


    int add(OrderList order);

    OrderList findById(Integer id);


    boolean hitJack(Integer id);

    int insertByOrderList(OrderList orderList);
}
