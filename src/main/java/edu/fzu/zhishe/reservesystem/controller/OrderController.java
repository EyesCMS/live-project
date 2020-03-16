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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xjliang
 */

@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private DateUtil dateUtil;

    private int taskId;

    @RequestMapping("/index")
    public String index() {
        return "redirect:/task";
    }

    @RequestMapping("/index/task")
    public String indexWithTaskId(@RequestParam(value="taskId") String task_id) {
        taskId = Integer.parseInt(task_id);
        return "order/index";
    }

    @PostMapping("/query")
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
                return new ModelAndView("order/result", "orderResultModel", model);
            } else {
                Task task = taskService.findById(taskId);
                model.addAttribute("startTime", dateUtil.format(task.getStartTime()));
                model.addAttribute("endTime", dateUtil.format(task.getEndTime()));
                return new ModelAndView("order/not_finished", "notFinishedModel", model);
            }
        }
        return new ModelAndView("order/index");
    }


    @PostMapping("/add")
    public String doOrder(
        @RequestParam(value="name") String name,
        @RequestParam(value="idNumber") String idNum,
        @RequestParam(value="tel") String tel,
        @RequestParam(value="maskNum") String num)
    {

        //@RequestParam(value="task_id") String task_id,   这个等前端task_id表单写好粘贴到+++++处
        //System.out.println(taskId);
        OrderList orderList = new OrderList();
        orderList.setIdCard(idNum);
        orderList.setName(name);
        orderList.setPhone(tel);
        orderList.setNum(Integer.parseInt(num));
        orderList.setTaskId(taskId);//这里留作task_id填写
        orderList.setSuccess(0);//如果0代表没中

        System.out.println(orderList.toString());
        orderService.add(orderList);
        return "order/index";
    }
}
