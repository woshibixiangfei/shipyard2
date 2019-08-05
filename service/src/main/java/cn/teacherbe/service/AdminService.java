package cn.teacherbe.service;

import cn.teacherbe.entity.Admin;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;

public interface AdminService {

    Admin selectByPrimaryKey(Integer id);

    JSONObject login (Admin admin);

    public List<String> getRole(String admin);

    public String getRoleList(String admin,Integer pageNo,Integer pageSize);

    public String addRole(Admin admin,String idGroup,String account);

    public String delete(String admin);

    public String updateRole(Admin admin,String idGroup,String account);

    public List getBankListByExcel(InputStream in, String fileName) throws Exception;

    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception;
}
