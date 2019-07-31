package cn.teacherbe.dao;

import cn.teacherbe.entity.AccCommonInfo;
import cn.teacherbe.entity.AccInecomingInfo;
import cn.teacherbe.entity.PairEntity;
import cn.teacherbe.entity.SeplenishmentConfirm2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccCommonInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccCommonInfo record);

    int insertSelective(AccCommonInfo record);

    AccCommonInfo selectByPrimaryKey(Integer id);

    List<AccCommonInfo> selectAll(@Param("status")Integer status,@Param("shipNumber")String shipNumber,@Param("segmentation")String segmentation,
                                  @Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    Integer selectAllCount(@Param("status")Integer status,@Param("shipNumber")String shipNumber,@Param("segmentation")String segmentation);

    List<AccCommonInfo> selectInvoiceInfo(@Param("status")Integer status,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    Integer selectInvoiceInfoCount(@Param("status")Integer status);

    List<AccCommonInfo> selectPair();

    List<AccInecomingInfo> selectAccInecomingInfo(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    Integer selectAccInecomingInfoCount();

    List<PairEntity> selectUnPair();

    Integer getTotal();
    Integer getDone();
    Integer getUnDone();

    int updateById(@Param("updater")String updater,@Param("updateDate")String updateDate,@Param("idGroup")List<String> idGroup);

    int updateById2(@Param("updater")String updater,@Param("updateDate")String updateDate,@Param("idGroup")List<String> idGroup);

    int updateById3(@Param("updater")String updater,@Param("updateDate")String updateDate);

    int updateById4(@Param("commonId")Integer commonId,@Param("commonId2")Integer commonId2);

    int updateReplenishment(@Param("idGroup")List<SeplenishmentConfirm2> idGroup, @Param("updater")String admin);

    int updateByPrimaryKeySelective(AccCommonInfo record);

    int updateByPrimaryKey(AccCommonInfo record);
}