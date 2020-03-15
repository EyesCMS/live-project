package edu.fzu.zhishe.reservesystem.controller;

import edu.fzu.zhishe.reservesystem.generator.OrderList;
import edu.fzu.zhishe.reservesystem.service.OrderService;
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

   /*
    @PostMapping("/user")
    public String doOrder(@RequestParam(value="name") String name,
                          @RequestParam(value="idNumber") String idNum,
                          @RequestParam(value="tel") String tel,
                          @RequestParam(value="maskNum") String num)
    {

    }

    */
}
