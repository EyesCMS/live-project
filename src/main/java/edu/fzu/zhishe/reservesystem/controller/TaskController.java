package edu.fzu.zhishe.reservesystem.controller;

import edu.fzu.zhishe.reservesystem.generator.Task;
import edu.fzu.zhishe.reservesystem.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/task")
    public ModelAndView task() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order/task");

        List<Task> tasks = taskService.findAll();
        modelAndView.addObject("tasks", tasks);

        return modelAndView;
    }
}
