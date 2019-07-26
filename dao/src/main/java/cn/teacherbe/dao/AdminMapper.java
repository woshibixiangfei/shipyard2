package cn.teacherbe.dao;

import cn.teacherbe.entity.Admin;
import cn.teacherbe.entity.AdminRoleModel;

public interface AdminMapper {
    int deleteByPrimaryKey(Admin admin);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Admin admin);

    AdminRoleModel login (Admin admin);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}