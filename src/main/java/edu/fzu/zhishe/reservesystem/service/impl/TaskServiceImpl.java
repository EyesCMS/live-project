package edu.fzu.zhishe.reservesystem.service.impl;

import edu.fzu.zhishe.reservesystem.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public boolean started(Integer id) {
        return false;
    }

    @Override
    public boolean finished(Integer id) {
        return false;
    }
}