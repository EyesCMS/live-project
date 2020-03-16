package edu.fzu.zhishe.reservesystem.service.impl;

import edu.fzu.zhishe.reservesystem.generator.Task;
import edu.fzu.zhishe.reservesystem.generator.TaskDao;
import edu.fzu.zhishe.reservesystem.service.TaskService;
import edu.fzu.zhishe.reservesystem.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
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
    public List<Task> findAll() {
        return taskDao.selectByExample(null);
    }

    @Override
    public Task findById(Integer id) {
        return taskDao.selectByPrimaryKey(id);
    }

    @Override
    public int insertByTask(Task task) {
        return taskDao.insert(task);
    }


    @Override
    public boolean isLegalInput(String startDate,String endDate,String startTime,String endTime,String maxSingleNum,String maxTotalNum) throws ParseException {
        if(startDate.isEmpty()||endDate.isEmpty()||startTime.isEmpty()||endTime.isEmpty()||maxSingleNum.isEmpty()||maxTotalNum.isEmpty()){
            System.out.println("您没有填完数据");
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
        Date startFullDate = simpleDateFormat.parse(startDate + "-" + startTime);
        Date endFullDate = simpleDateFormat.parse(endDate + "-" + endTime);
        //判断时间先后顺序
        if(endFullDate.before(startFullDate)){
            System.out.println("结束日期不可早于开始日期");
            return false;
        }
        //判断单次最大数是否大于总数
        if(Integer.parseInt(maxSingleNum)>Integer.parseInt(maxTotalNum)){
            System.out.println("单个用户最高可预约口罩数量不得大于总数");
            return false;
        }
        return true;
    }

    @Override
    public Task taskCreate(String startDate,String endDate,String startTime,String endTime,String maxSingleNum,String maxTotalNum) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
        Date startFullDate = simpleDateFormat.parse(startDate + "-" + startTime);
        Date endFullDate = simpleDateFormat.parse(endDate + "-" + endTime);

        Task task = new Task();
        task.setStartTime(startFullDate);
        task.setEndTime(endFullDate);
        task.setMaxNum(Integer.parseInt(maxSingleNum));
        task.setTotalNum(Integer.parseInt(maxTotalNum));
        return task;
    }
}
