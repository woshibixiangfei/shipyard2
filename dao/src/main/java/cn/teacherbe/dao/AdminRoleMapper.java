package cn.teacherbe.dao;

import cn.teacherbe.entity.AdminRole;
import cn.teacherbe.entity.RealRole;

import java.util.List;

public interface AdminRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    AdminRole selectByPrimaryKey(Integer id);

    List<RealRole> selectByAdminId(Integer adminId);

    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);
}