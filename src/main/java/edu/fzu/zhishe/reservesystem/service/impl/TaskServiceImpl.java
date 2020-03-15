package edu.fzu.zhishe.reservesystem.service.impl;

import edu.fzu.zhishe.reservesystem.generator.Task;
import edu.fzu.zhishe.reservesystem.generator.TaskDao;
import edu.fzu.zhishe.reservesystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDao taskDao;

    @Override
    public boolean started(Integer id) {
        return false;
    }

    @Override
    public boolean finished(Integer id) {
        return false;
    }

    @Override
    public Task findById(Integer id) {
        return taskDao.selectByPrimaryKey(id);
    }
}
