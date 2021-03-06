package edu.fzu.zhishe.reservesystem.generator;

import edu.fzu.zhishe.reservesystem.generator.Task;
import edu.fzu.zhishe.reservesystem.generator.TaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskDao {
    long countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}