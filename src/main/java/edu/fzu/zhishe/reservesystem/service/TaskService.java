package edu.fzu.zhishe.reservesystem.service;

import edu.fzu.zhishe.reservesystem.generator.Task;

import java.text.ParseException;
import java.util.List;

public interface TaskService {

    boolean started(Integer id);

    boolean finished(Integer id);

    List<Task> findAll();

    Task findById(Integer id);

    int insertByTask(Task task);

    boolean isLegalInput(String startDate,String endDate,String startTime,String endTime,String maxSingleNum,String maxTotalNum) throws ParseException;

    Task taskCreate(String startDate,String endDate,String startTime,String endTime,String maxSingleNum,String maxTotalNum) throws ParseException;
}
