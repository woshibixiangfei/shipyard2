package cn.teacherbe.dao;

import cn.teacherbe.entity.WorkItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("no")String updater, @Param("idGroup") List<String> idGroup,@Param("type")Integer type,@Param("typeName")String typeName);

    int insertSelective(WorkItem record);

    WorkItem selectByPrimaryKey(Integer id);

    List<WorkItem> selectByWrokId(@Param("workItemId")Integer workItemId,@Param("type")Integer type);

    List<Integer> selectProcess(@Param("id")Integer id);

    int updateByPrimaryKeySelective(WorkItem record);

    int updateByPrimaryKey(WorkItem record);
}