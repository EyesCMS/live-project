package edu.fzu.zhishe.reservesystem.controller;

import edu.fzu.zhishe.reservesystem.generator.OrderList;
import edu.fzu.zhishe.reservesystem.service.OrderService;
import edu.fzu.zhishe.reservesystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xjliang
 */

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TaskService taskService;

    @PostMapping("/order")
    public ModelAndView queryOrderResult(@RequestParam("orderNumber") Integer orderId, Model model) {
        OrderList order = orderService.findById(orderId);
        if (order != null) {
            Integer taskId = order.getTaskId();
            if (taskService.finished(taskId)) {
                model.addAttribute("random_result", orderService.hitJack(orderId));
                return new ModelAndView("result", "orderResultModel", model);
            } else {
                return new ModelAndView("not_finished");
            }
        }
        return new ModelAndView("user");
    }
}
