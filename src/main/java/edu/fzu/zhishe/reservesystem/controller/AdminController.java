package edu.fzu.zhishe.reservesystem.controller;

import edu.fzu.zhishe.reservesystem.generator.Task;
import edu.fzu.zhishe.reservesystem.generator.TaskDao;
import edu.fzu.zhishe.reservesystem.generator.TaskExample;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminController {
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/admin")
    public String admin(@RequestParam(value = "starDate") String startDate,
                        @RequestParam(value = "endDate") String endDate,
                        @RequestParam(value = "starTime") String startTime,
                        @RequestParam(value = "endTime") String endTime,
                        @RequestParam(value = "max_num") int maxSingleNum,
                        @RequestParam(value = "total_num") int maxTotalNum) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date startFullDate = simpleDateFormat.parse(startDate + " " + startTime);
        Date endFullDate = simpleDateFormat.parse(endDate + " " + endTime);
        Task task = new Task();
        task.setStartTime(startFullDate);
        task.setEndTime(endFullDate);
        task.setMaxNum(maxSingleNum);
        task.setTotalNum(maxTotalNum);
        System.out.println(startFullDate);
        System.out.println(endFullDate);
        System.out.println(maxSingleNum);
        System.out.println(maxTotalNum);
        //int count = TaskDao.insert();
        return "admin";

    }
}

