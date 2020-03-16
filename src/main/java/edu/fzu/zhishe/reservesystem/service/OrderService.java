package edu.fzu.zhishe.reservesystem.service;

import edu.fzu.zhishe.reservesystem.generator.OrderList;
import edu.fzu.zhishe.reservesystem.generator.Task;

public interface OrderService {


    int add(OrderList order);

    OrderList findById(Integer id);


    boolean hitJack(Integer id);

    int insertByOrderList(OrderList orderList);

    boolean isLegalOrder( String name,String idNum,String tel,String num,int task_id);

    OrderList findByTel(String tel,int taskId);

    OrderList findByIdNum(String idNum,int taskId);
}
