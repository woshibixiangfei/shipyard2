package cn.teacherbe.dao;

import cn.teacherbe.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccCommonInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccCommonInfo record);

    int insertSelective(AccCommonInfo record);

    AccCommonInfo selectByPrimaryKey(Integer id);

    List<AccCommonInfo> selectAll(@Param("status")Integer status,@Param("shipNumber")String shipNumber,@Param("segmentation")String segmentation,
                                  @Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    List<AccCommonInfo> selectAll2(@Param("shipNumber")String shipNumber,@Param("segmentation")String segmentation,
                                  @Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("type")String type,@Param("startDate") String startDate, @Param("endDate") String endDate);

    Integer selectAllCount(@Param("status")Integer status,@Param("shipNumber")String shipNumber,@Param("segmentation")String segmentation);

    Integer selectAll3Count(@Param("shipNumber")String shipNumber,@Param("segmentation")String segmentation);

    Integer selectAll2Count(@Param("shipNumber")String shipNumber,@Param("segmentation")String segmentation,@Param("type")String type,@Param("startDate") String startDate, @Param("endDate") String endDate);

    Integer selectAll2Day(@Param("shipNumber")String shipNumber,@Param("segmentation")String segmentation);
    Integer selectAll2Month(@Param("shipNumber")String shipNumber,@Param("segmentation")String segmentation);

    List<AccCommonInfo> selectInvoiceInfo(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<FuckEveryDay> getFuckEveryDay(@Param("id")Integer id);
    Integer selectInvoiceInfoCount(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<AccCommonInfo> selectLajiInfo(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    Integer selectLajiInfoCount();

    List<AccCommonInfo> selectPair();

    List<AccInecomingInfo> selectAccInecomingInfo(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<String> selectCaonimaInfo();

    Integer selectAccInecomingInfoCount(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<PairEntity> selectUnPair();

    Integer getTotal();
    Integer getDone();
    Integer getUnDone();
    Integer getDoneDay();
    int sb(@Param("updater")String updater,@Param("updateDate")String updateDate,@Param("idGroup")List<String> idGroup);

    int updateById(@Param("updater")String updater,@Param("updateDate")String updateDate,@Param("idGroup")List<DASHABI> idGroup);

    int updateById2(@Param("updater")String updater,@Param("updateDate")String updateDate,@Param("idGroup")List<String> idGroup);

    int updateById3(@Param("updater")String updater,@Param("updateDate")String updateDate);

    int updateById4(@Param("commonId")Integer commonId,@Param("commonId2")Integer commonId2);

    int updateById5(@Param("text")String text,@Param("id")Integer id,@Param("updater")String admin);

    int updateById6(@Param("text")String text,@Param("id")Integer id,@Param("updater")String admin);

    int updateReplenishment(@Param("idGroup")List<SeplenishmentConfirm2> idGroup, @Param("updater")String admin);

    int laji(@Param("idGroup")List<Text> idGroup, @Param("updater")String updater);

    int updateByPrimaryKeySelective(AccCommonInfo record);

    int updateByPrimaryKey(AccCommonInfo record);
}