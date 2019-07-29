package cn.teacherbe.service;

import cn.teacherbe.entity.Admin;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;

public interface AdminService {

    Admin selectByPrimaryKey(Admin admin);

    JSONObject login (Admin admin);

    public List getBankListByExcel(InputStream in, String fileName) throws Exception;

    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception;
}
