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
        //判断是否填写数据
        if(startDate.isEmpty()||endDate.isEmpty()||startTime.isEmpty()||endTime.isEmpty()||maxSingleNum.isEmpty()||maxTotalNum.isEmpty()){
            System.out.println("您没有填完数据");
            return "admin/index";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
        Date startFullDate = simpleDateFormat.parse(startDate + "-" + startTime);
        Date endFullDate = simpleDateFormat.parse(endDate + "-" + endTime);
        //判断时间先后顺序
        if(endFullDate.before(startFullDate)){
            System.out.println("结束日期不可早于开始日期");
            return "admin/index";
        }
        //判断单次最大数是否大于总数
        if(Integer.parseInt(maxSingleNum)>Integer.parseInt(maxTotalNum)){
            System.out.println("单个用户最高可预约口罩数量不得大于总数");
            return "admin/index";
        }
        Task task = new Task();
        task.setStartTime(startFullDate);
        task.setEndTime(endFullDate);
        task.setMaxNum(Integer.parseInt(maxSingleNum));
        task.setTotalNum(Integer.parseInt(maxTotalNum));
        taskService.insertByTask(task);

        System.out.println("任务发布成功");

        return "redirect:/task";
//        return "admin/task";
    }
}


