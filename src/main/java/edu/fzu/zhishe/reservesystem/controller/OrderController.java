package edu.fzu.zhishe.reservesystem.controller;

import edu.fzu.zhishe.reservesystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xjliang
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{task_id}")
    public String queryOrderResult(@PathVariable("task_id") Integer taskId) {
        if (taskService.finished(taskId)) {
            return "result";
        } else {
            return "not_finished";
        }
    }
}
