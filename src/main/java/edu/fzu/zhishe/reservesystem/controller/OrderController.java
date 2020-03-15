package edu.fzu.zhishe.reservesystem.controller;

import edu.fzu.zhishe.reservesystem.generator.OrderList;
import edu.fzu.zhishe.reservesystem.generator.Task;
import edu.fzu.zhishe.reservesystem.service.OrderService;
import edu.fzu.zhishe.reservesystem.service.TaskService;
import edu.fzu.zhishe.reservesystem.util.DateUtil;
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

    @Autowired
    private DateUtil dateUtil;

    @PostMapping("/order")
    public ModelAndView queryOrderResult(@RequestParam("orderNumber") Integer orderId, Model model) {
        OrderList order = orderService.findById(orderId);
        if (order != null) {
            Integer taskId = order.getTaskId();
            if (taskService.finished(taskId)) {
                boolean hitJack = orderService.hitJack(orderId);
                if (hitJack) {
                    model.addAttribute("order", order);
                }
                model.addAttribute("random_result", hitJack);
                return new ModelAndView("result", "orderResultModel", model);
            } else {
                Task task = taskService.findById(taskId);
                model.addAttribute("startTime", dateUtil.format(task.getStartTime()));
                model.addAttribute("endTime", dateUtil.format(task.getEndTime()));
                return new ModelAndView("not_finished", "notFinishedModel", model);
            }
        }
        return new ModelAndView("user");
    }
}
