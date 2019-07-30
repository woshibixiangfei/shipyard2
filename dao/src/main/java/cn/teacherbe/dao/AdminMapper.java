package cn.teacherbe.dao;

import cn.teacherbe.entity.Admin;
import cn.teacherbe.entity.RoleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("name")String name,@Param("department")String department,
               @Param("post")String post,
               @Param("no")String no,
               @Param("password")String password,
               @Param("account") String admin);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    Admin selectByNo(@Param("no")String no, @Param("password")String password);

    Admin selectByAdmin(@Param("no")String no);

    List<RoleList> selectRoleList(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    Integer selectRoleListCount();

    public String addRole(String admin);

    String selectRoleList2(@Param("no")String admin);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(@Param("id")Integer id,@Param("name")String name,@Param("department")String department,
                           @Param("post")String post,
                           @Param("no")String no,
                           @Param("password")String password,
                           @Param("account") String admin);
}