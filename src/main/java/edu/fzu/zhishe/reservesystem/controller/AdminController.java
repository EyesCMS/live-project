package edu.fzu.zhishe.reservesystem.controller;

import edu.fzu.zhishe.reservesystem.generator.Task;
import edu.fzu.zhishe.reservesystem.generator.TaskDao;
import edu.fzu.zhishe.reservesystem.generator.TaskExample;

import edu.fzu.zhishe.reservesystem.service.TaskService;
import edu.fzu.zhishe.reservesystem.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/admin")
    public String admin(@RequestParam(value = "starDate") String startDate,
                        @RequestParam(value = "endDate") String endDate,
                        @RequestParam(value = "starTime") String startTime,
                        @RequestParam(value = "endTime") String endTime,
                        @RequestParam(value = "max_num") String maxSingleNum,
                        @RequestParam(value = "total_num") String maxTotalNum) throws ParseException {
        //判断是否填写数据
        if(startDate.isEmpty()||endDate.isEmpty()||startTime.isEmpty()||endTime.isEmpty()||maxSingleNum.isEmpty()||maxTotalNum.isEmpty()){
            System.out.println("您没有填完数据");
            return "admin";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
        Date startFullDate = simpleDateFormat.parse(startDate + "-" + startTime);
        Date endFullDate = simpleDateFormat.parse(endDate + "-" + endTime);
        //判断时间先后顺序
        if(endFullDate.before(startFullDate)){
            System.out.println("结束日期不可早于开始日期");
            return "admin";
        }
        //判断单次最大数是否大于总数
        if(Integer.parseInt(maxSingleNum)>Integer.parseInt(maxTotalNum)){
            System.out.println("单个用户最高可预约口罩数量不得大于总数");
            return "admin";
        }
        Task task = new Task();
        task.setStartTime(startFullDate);
        task.setEndTime(endFullDate);
        task.setMaxNum(Integer.parseInt(maxSingleNum));
        task.setTotalNum(Integer.parseInt(maxTotalNum));
        taskService.insertByTask(task);

        System.out.println("任务发布成功");
        return "task";

    }
}


