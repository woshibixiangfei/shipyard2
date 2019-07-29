package cn.teacherbe.service.impl;

import cn.teacherbe.dao.AdminMapper;
import cn.teacherbe.entity.Admin;
import cn.teacherbe.entity.RealRole;
import cn.teacherbe.service.AdminService;
import cn.teacherbe.utils.StringUtil;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin selectByPrimaryKey(Integer id ) {
        return this.adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public JSONObject login(Admin admin) {
        JSONObject loginMap = new JSONObject();
        if(StringUtil.notEmpty(admin.getNo()) && StringUtil.notEmpty(admin.getPassword())){
            Admin adminList = this.adminMapper.selectByNo(admin.getNo(),admin.getPassword());
            if(adminList == null){
                loginMap.put("status","failed");
                loginMap.put("info","账号或密码错误！");
            } else {

                loginMap.put("status","success");
                loginMap.put("info","账号密码匹配成功！");
                List<RealRole> realRoleList = this
                loginMap.put("information",roleMap.toString());
            }
        } else {
            loginMap.put("status","failed");
            loginMap.put("info","账号密码不能为空！");
        }
        return loginMap;
    }

    /**
     * 处理上传的文件
     *
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    public List getBankListByExcel(InputStream in, String fileName) throws Exception {
        List list = new ArrayList<>();
        //创建Excel工作薄
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(cell);
                }
                list.add(li);
            }
        }
        work.close();
        return list;
    }

    /**
     * 判断文件格式
     *
     * @param inStr
     * @param fileName
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }
}
