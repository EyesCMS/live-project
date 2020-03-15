package edu.fzu.zhishe.reservesystem.service;

import edu.fzu.zhishe.reservesystem.generator.Task;
import java.util.List;

public interface TaskService {

    boolean started(Integer id);

    boolean finished(Integer id);

    List<Task> findAll();

    Task findById(Integer id);

    int insertByTask(Task task);
}
