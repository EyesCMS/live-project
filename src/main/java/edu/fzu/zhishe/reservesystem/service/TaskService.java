package edu.fzu.zhishe.reservesystem.service;

public interface TaskService {

    boolean started(Integer id);

    boolean finished(Integer id);
}
