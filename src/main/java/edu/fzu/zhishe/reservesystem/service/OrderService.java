package edu.fzu.zhishe.reservesystem.service;

import edu.fzu.zhishe.reservesystem.generator.OrderList;
import edu.fzu.zhishe.reservesystem.generator.Task;

public interface OrderService {


    int add(OrderList order);

    OrderList findById(Integer id);


    boolean hitJack(Integer id);

    OrderList orderListCreate(String name,String idNum,String tel,String num,int task_id);

    boolean isLegalOrder( String name,String idNum,String tel,String num,int task_id);

    int isHitBefore( String name,String idNum,String tel,String num,int task_id);

    OrderList findByTelAndTaskId(String tel,int taskId);

    OrderList findByIdNumAndTaskId(String idNum,int taskId);
}
