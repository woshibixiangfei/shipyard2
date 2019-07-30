package cn.teacherbe.dao;

import cn.teacherbe.entity.ShipNumber;

import java.util.List;

public interface ShipNumberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShipNumber record);

    int insertSelective(ShipNumber record);

    ShipNumber selectByPrimaryKey(Integer id);

    List<String> selectByAll();

    ShipNumber selectByNumber(String shipNumber);

    int updateByPrimaryKeySelective(ShipNumber record);

    int updateByPrimaryKey(ShipNumber record);
}