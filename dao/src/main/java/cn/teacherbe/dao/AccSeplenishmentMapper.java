package cn.teacherbe.dao;

import cn.teacherbe.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccSeplenishmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccSeplenishment record);

    int insertInit(@Param("idGroup")List<String> idGroup,@Param("updater")String updater,
                   @Param("updateDate")String updateDate,@Param("creator")String creator,@Param("registerDate")String registerDate);

    int insertSelective(AccSeplenishment record);

    AccSeplenishment selectByPrimaryKey(Integer id);

    List<AccSeplenishmentInfo> selectAll(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    Integer selectAllCount();

    int seplenishmentConfirm1(@Param("idGroup")List<SeplenishmentConfirm1> idGroup,@Param("updater")String admin);

    int seplenishmentConfirm2(@Param("idGroup")List<SeplenishmentConfirm2> idGroup, @Param("updater")String admin);

    List<PairEntity> selectUnPair(@Param("idGroup")List<SeplenishmentConfirm2> idGroup);

    int updateByPrimaryKeySelective(AccSeplenishment record);

    int updateByPrimaryKey(AccSeplenishment record);
}