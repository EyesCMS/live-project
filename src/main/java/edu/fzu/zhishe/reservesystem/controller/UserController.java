package edu.fzu.zhishe.reservesystem.controller;

import edu.fzu.zhishe.reservesystem.generator.OrderList;
import edu.fzu.zhishe.reservesystem.service.OrderService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private OrderService orderService;

    @RequestMapping("/user")
    public String user() {
        return "user";
    }


    @PostMapping("/user")
    public String doOrder(@RequestParam(value="task_id") String task_id,
                          @RequestParam(value="name") String name,
                          @RequestParam(value="idNumber") String idNum,
                          @RequestParam(value="tel") String tel,
                          @RequestParam(value="maskNum") String num)
    {


        OrderList orderList = new OrderList();
        orderList.setIdCard(idNum);
        orderList.setName(name);
        orderList.setPhone(tel);
        orderList.setNum(Integer.parseInt(num));
        orderList.setTaskId(1);//这里留作task_id填写
        orderList.setSuccess(0);//如果0代表没中签
        System.out.println(name);
        System.out.println(idNum);
        System.out.println(tel);
        System.out.println(num);


        System.out.println(orderList.toString());
        orderService.add(orderList);
        return "user";
    }


}
