package cn.teacherbe.dao;

import cn.teacherbe.entity.AdminDepartment;

public interface AdminDepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminDepartment record);

    int insertSelective(AdminDepartment record);

    AdminDepartment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminDepartment record);

    int updateByPrimaryKey(AdminDepartment record);
}