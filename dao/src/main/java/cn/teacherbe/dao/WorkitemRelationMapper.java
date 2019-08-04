package cn.teacherbe.dao;

import cn.teacherbe.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkitemRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkitemRelation record);

    int insertSelective(WorkitemRelation record);

    WorkitemRelation selectByPrimaryKey(Integer id);

    List<WorkitemRelation> selectByCommonId(@Param("commonId") Integer commonId);

    List<WorkitemRelation> selectByCommonId2(@Param("commonId2") Integer commonId2);

    List<WorkitemRelationPlan> selectPlan(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    List<FuckYou> selectFuckyou(@Param("id")Integer id);

    List<WorkitemRelationPlan> selectAssemblyInfo(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("no")String admin);

    List<WorkitemRelationPlan> selectWeldingInfo(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("no")String admin,@Param("type")Integer type);

    List<OutInfo> selectOutInfo(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    Integer getWelding12345Day(@Param("type")Integer type);

    Integer getWelding12345Month(@Param("type")Integer type);

    Integer selectOutInfoCount();

    Integer selectPlanCount();

    Integer getWeldingAll();

    Integer getTotal();

    Integer getDone();

    Integer selectAssemblyInfoCount(@Param("no")String admin);

    List<TaskInfo> selectTaskInfo(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("no")String admin);

    Integer selectTaskInfoCount(@Param("no")String admin);

    Integer selectWeldingInfoCount(@Param("no")String admin,@Param("type")Integer type);

    List<WorkItem> getWeldingSelect(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    Integer getWeldingSelectCount();

    int underPlan(@Param("updater")String updater,@Param("idGroup")List<String> idGroup,@Param("kua")String kua);

    int claim(@Param("idGroup")List<String> idGroup,@Param("no")String no);

    int assembly(@Param("idGroup")List<AssemblyInfo> idGroup, @Param("no")String no);

    int done(@Param("id")Integer id, @Param("no")String no);

    int out(@Param("idGroup")List<String> idGroup,  @Param("carNumber")String carNumber,@Param("no")String no);

    int updateByPrimaryKeySelective(WorkitemRelation record);

    int updateByPrimaryKey(WorkitemRelation record);
}