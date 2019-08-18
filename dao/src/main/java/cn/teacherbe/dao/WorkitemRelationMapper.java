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

    List<WorkitemRelationPlan> selectPlan(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<FuckYou> selectFuckyou(@Param("id")Integer id);

    List<WorkitemRelationPlan> selectAssemblyInfo(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("no")String admin,@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<WorkitemRelationPlan> selectWeldingInfo(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("no")String admin,@Param("type")Integer type,@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<OutInfo> selectOutInfo(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("startDate") String startDate, @Param("endDate") String endDate);

    Integer getWelding12345Day(@Param("type")Integer type);

    Integer getWelding12345Month(@Param("type")Integer type);

    Integer selectOutInfoCount(@Param("startDate") String startDate, @Param("endDate") String endDate);

    Integer selectPlanCount(@Param("startDate") String startDate, @Param("endDate") String endDate);

    Integer getWeldingAll();

    Integer getTotal();

    Integer getDone();

    Integer selectAssemblyInfoCount(@Param("no")String admin,@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<TaskInfo> selectTaskInfo(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("no")String admin,@Param("startDate") String startDate, @Param("endDate") String endDate);

    Integer selectTaskInfoCount(@Param("no")String admin,@Param("startDate") String startDate, @Param("endDate") String endDate);

    Integer selectWeldingInfoCount(@Param("no")String admin,@Param("type")Integer type,@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<WorkItem> getWeldingSelect(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize,@Param("startDate") String startDate, @Param("endDate") String endDate);

    Integer getWeldingSelectCount(@Param("startDate") String startDate, @Param("endDate") String endDate);

    int a(@Param("id")Integer id);

    int b(@Param("id")Integer id);

    int c(@Param("id")Integer id,@Param("updater")String updater);

    int underPlan(@Param("updater")String updater,@Param("idGroup")List<UnderPlan> idGroup);

    int underPlan2(@Param("updater")String updater,@Param("idGroup")List<UnderPlan> idGroup);

    int claim(@Param("idGroup")List<String> idGroup,@Param("no")String no);

    int assembly(@Param("idGroup")List<AssemblyInfo> idGroup, @Param("no")String no);

    int assembly2(@Param("idGroup")List<AssemblyInfo> idGroup);

    int done(@Param("id")Integer id, @Param("no")String no);

    int insertDaSB(@Param("id")Integer id);

    int out(@Param("idGroup")List<String> idGroup,  @Param("carNumber")String carNumber,@Param("no")String no);

    int updateByPrimaryKeySelective(WorkitemRelation record);

    int updateByPrimaryKey(WorkitemRelation record);
}