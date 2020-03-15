package edu.fzu.zhishe.reservesystem.service.impl;

import edu.fzu.zhishe.reservesystem.generator.Task;
import edu.fzu.zhishe.reservesystem.generator.TaskDao;
import edu.fzu.zhishe.reservesystem.service.TaskService;
import edu.fzu.zhishe.reservesystem.util.DateUtil;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private DateUtil dateUtil;

    @Override
    public boolean started(Integer id) {
        Date startTime = taskDao.selectByPrimaryKey(id).getStartTime();
        String currentTime = dateUtil.format(new Date());
        return currentTime.compareTo(startTime.toString()) <= 0;
    }

    @Override
    public boolean finished(Integer id) {
        Date endTime = taskDao.selectByPrimaryKey(id).getEndTime();
        String currentTime = dateUtil.format(new Date());
        return currentTime.compareTo(dateUtil.format(endTime)) >= 0;
    }

    @Override
    public Task findById(Integer id) {
        return taskDao.selectByPrimaryKey(id);
    }

    @Override
    public int insertByTask(Task task) {
        return taskDao.insert(task);
    }
}
