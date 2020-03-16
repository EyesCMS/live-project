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
    public int isHitBefore(String name, String idNum, String tel, String num, int task_id) {
        int count1 = 0;
        int count2 = 0;
        int hitTaskId = 0;
        //身份证号判断
        System.out.println("身份证号判断");
        for(int i = task_id - 1;i>0;i--){
            count1++;
            if(count1>3){
                break;
            }
            OrderList orderList1 = findByIdNumAndTaskId(idNum,i);
            if(orderList1==null){
                //System.out.println("身份证号：" + idNum + "没有参加第" + i + "次活动");
                continue;
            }
            //System.out.println("身份证号：" + idNum + "有参加第" + i + "次活动");
            if(orderList1.getSuccess()==1){
                System.out.println("身份证号：" + idNum + "参加第" + i + "次活动中奖");
                hitTaskId = i;
                return hitTaskId;
            }
        }
        System.out.println("手机号判断");
        //手机号判断
        for(int i = task_id - 1;i>0;i--){
            count2++;
            if(count2>3){
                break;
            }
            OrderList orderList2 = findByTelAndTaskId(tel,i);
            if(orderList2==null){
                //System.out.println("手机号：" + tel + "没有参加第" + i + "次活动");
                continue;
            }
            //System.out.println("手机号：" + tel + "有参加第" + i + "次活动");
            if(orderList2.getSuccess()==1){
                System.out.println("手机号：" + tel + "参加第" + i + "次活动中奖");
                hitTaskId = i;
                return hitTaskId;
            }
        }
        //System.out.println(hitTaskId);
        return hitTaskId;
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
