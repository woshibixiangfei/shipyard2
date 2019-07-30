package cn.teacherbe.dao;

import cn.teacherbe.entity.AdminRole;
import cn.teacherbe.entity.RealRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByAdminId(Integer adminId);

    int insert(@Param("adminId")Integer adminId,
               @Param("roleId")Integer roleId,
               @Param("account")String admin);

    int insertSelective(AdminRole record);

    AdminRole selectByPrimaryKey(Integer id);

    List<RealRole> selectByAdminId(Integer adminId);

    List<AdminRole> selectByRoleId(@Param("adminId") Integer adminId,@Param("roleId") Integer roleId);

    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);
}