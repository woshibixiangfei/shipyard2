package cn.teacherbe.dao;

import cn.teacherbe.entity.WorkItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkItemMapper {
    int deleteByPrimaryKey(Integer id);

    List<Integer> selectProcess(@Param("id")Integer id);

    WorkItem selectByWorkId(Integer workItemId);

    int insertInit(@Param("idGroup") List<String> idGroup,@Param("no")String no);

    int insert(WorkItem record);

    int insertSelective(WorkItem record);

    WorkItem selectByPrimaryKey(Integer id);

    int updateWorkitem1(@Param("idGroup") List<String> idGroup,@Param("no")String no);

    int updateWorkitem2(@Param("idGroup") List<String> idGroup,@Param("no")String no);

    int updateWorkitem3(@Param("idGroup") List<String> idGroup,@Param("no")String no);

    int updateWorkitem4(@Param("idGroup") List<String> idGroup,@Param("no")String no);

    int updateByPrimaryKeySelective(WorkItem record);

    int updateByPrimaryKey(WorkItem record);
}