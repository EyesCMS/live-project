package edu.fzu.zhishe.reservesystem.service.impl;

import edu.fzu.zhishe.reservesystem.generator.OrderList;
import edu.fzu.zhishe.reservesystem.generator.OrderListDao;
import edu.fzu.zhishe.reservesystem.generator.OrderListExample;
import edu.fzu.zhishe.reservesystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xjliang
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderListDao orderListDao;

    @Override
    public OrderList findById(Integer id) {
        return orderListDao.selectByPrimaryKey(id);
    }

    @Override
    public int add(OrderList order) {
        return orderListDao.insert(order);
    }

    @Override
    public boolean hitJack(Integer id) {
        OrderList order = orderListDao.selectByPrimaryKey(id);
        if (order != null) {
            return order.getSuccess() != 0;
        }
        return false;
    }

    @Override
    public OrderList orderListCreate(String name, String idNum, String tel, String num, int task_id) {
        OrderList orderList = new OrderList();

        orderList.setIdCard(idNum);
        orderList.setName(name);
        orderList.setPhone(tel);
        orderList.setNum(Integer.parseInt(num));
        orderList.setTaskId(task_id);//这里留作task_id填写
        orderList.setSuccess(0);//如果0代表没中

        return orderList;
    }

    @Override
    public boolean isLegalOrder(String name, String idNum, String tel, String num, int task_id) {
        OrderList orderList1 = findByIdNumAndTaskId(idNum,task_id);
        if(orderList1 != null){
            System.out.println("该身份证号已经预约过");
            return false;
        }
        OrderList orderList2 = findByTelAndTaskId(tel,task_id);
        if(orderList2 != null){
            System.out.println("该手机号号已经预约过");
            return false;
        }
        return true;
    }

    @Override
    public OrderList findByTelAndTaskId(String tel,int taskId) {
        OrderListExample orderListExample = new OrderListExample();
        orderListExample.createCriteria()
                .andPhoneEqualTo(tel)
                .andTaskIdEqualTo(taskId);
        List<OrderList> orderLists = orderListDao.selectByExample(orderListExample);
        return orderLists.isEmpty()?null:orderLists.get(0);
    }

    @Override
    public OrderList findByIdNumAndTaskId(String idNum,int taskId) {
        OrderListExample orderListExample = new OrderListExample();
        orderListExample.createCriteria()
                .andIdCardEqualTo(idNum)
                .andTaskIdEqualTo(taskId);
        List<OrderList> orderLists = orderListDao.selectByExample(orderListExample);
        return orderLists.isEmpty()?null:orderLists.get(0);
    }
}
