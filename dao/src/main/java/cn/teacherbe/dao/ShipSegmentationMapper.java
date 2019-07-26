package cn.teacherbe.dao;

import cn.teacherbe.entity.Segmentation;
import cn.teacherbe.entity.ShipSegmentation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShipSegmentationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShipSegmentation record);

    int insertSelective(ShipSegmentation record);

    ShipSegmentation selectByPrimaryKey(Integer id);

    List<Segmentation> querySegmentation(String shipName);

    List<String> selectAll(@Param("shipNumber")String shipNumber,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    Integer selectAllCount(@Param("shipNumber")String shipNumber);

    int updateByPrimaryKeySelective(ShipSegmentation record);

    int updateByPrimaryKey(ShipSegmentation record);
}