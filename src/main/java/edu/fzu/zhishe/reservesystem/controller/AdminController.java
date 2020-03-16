package edu.fzu.zhishe.reservesystem.controller;

import edu.fzu.zhishe.reservesystem.generator.AdminUser;
import edu.fzu.zhishe.reservesystem.generator.Task;

import edu.fzu.zhishe.reservesystem.service.AdminService;
import edu.fzu.zhishe.reservesystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(value = "username") String username,
        @RequestParam(value = "password") String password) {
        AdminUser adminUser = adminService.findByNameAndPassword(username, password);
        if (adminUser == null) {
            return "admin/login";
        } else {
            return "admin/index";
        }
    }

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @PostMapping("/task")
    public String task(@RequestParam(value = "starDate") String startDate,
                        @RequestParam(value = "endDate") String endDate,
                        @RequestParam(value = "starTime") String startTime,
                        @RequestParam(value = "endTime") String endTime,
                        @RequestParam(value = "max_num") String maxSingleNum,
                        @RequestParam(value = "total_num") String maxTotalNum) throws ParseException {
        //判断填写数据是否合法
        if(!taskService.isLegalInput(startDate,endDate,startTime,endTime,maxSingleNum,maxTotalNum)){
            System.out.println("您的数据填写不合法");
            return "admin/index";
        }
        Task task = taskService.taskCreate(startDate,endDate,startTime,endTime,maxSingleNum,maxTotalNum);
        taskService.insertByTask(task);

        System.out.println("任务发布成功");

        return "redirect:/taskToAdmin";
//        return "admin/task";
    }
}


