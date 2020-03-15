package edu.fzu.zhishe.reservesystem.service.impl;

import edu.fzu.zhishe.reservesystem.generator.OrderList;
import edu.fzu.zhishe.reservesystem.generator.OrderListDao;
import edu.fzu.zhishe.reservesystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xjliang
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderListDao orderListDao;

    @Override
    public OrderList findById(Integer id) {
        return orderListDao.selectByPrimaryKey(id);
    }

    @Override
    public void add(OrderList order) {
        
    }

    @Override
    public boolean hitJack(Integer id) {
        OrderList order = orderListDao.selectByPrimaryKey(id);
        if (order != null) {
            return order.getSuccess() != 0;
        }
        return false;
    }
}
