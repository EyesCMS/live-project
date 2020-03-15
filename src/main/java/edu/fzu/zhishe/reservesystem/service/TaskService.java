package edu.fzu.zhishe.reservesystem.service;

import edu.fzu.zhishe.reservesystem.generator.Task;

public interface TaskService {

    boolean started(Integer id);

    boolean finished(Integer id);

    Task findById(Integer id);

    public int insertByTask(Task task);
}
