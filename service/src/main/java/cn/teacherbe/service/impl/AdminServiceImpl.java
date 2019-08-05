package cn.teacherbe.service.impl;

import cn.teacherbe.dao.AdminMapper;
import cn.teacherbe.dao.AdminRoleMapper;
import cn.teacherbe.entity.*;
import cn.teacherbe.service.AdminService;
import cn.teacherbe.utils.StringUtil;
import net.sf.json.JSONArray;
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
import java.util.Arrays;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public Admin selectByPrimaryKey(Integer id ) {
        return this.adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public JSONObject login(Admin admin) {
        try{
        JSONObject loginMap = new JSONObject();
        if(StringUtil.notEmpty(admin.getNo()) || StringUtil.notEmpty(admin.getPassword())){
            Admin adminList = this.adminMapper.selectByNo(admin.getNo(),admin.getPassword());
            if(adminList == null){
                loginMap.put("status","failed");
                loginMap.put("info","账号或密码错误！");
            } else {
                List<RealRole> realRoleList = this.adminRoleMapper.selectByAdminId(adminList.getId());
                JSONArray jsonObject = JSONArray.fromObject(realRoleList);
                loginMap.put("status","success");
                loginMap.put("info","账号密码匹配成功！");
                loginMap.put("information",jsonObject);
            }
        } else {
            loginMap.put("status","failed");
            loginMap.put("info","账号密码不能为空！");
        }
        return loginMap;
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json;
        }
    }

    @Override
    public List<String> getRole(String admin) {
        String idGroup = this.adminMapper.selectRoleList2(admin);
        List<String> result = Arrays.asList(idGroup.split(","));
        return result;
    }

    @Override
    public String getRoleList(String admin,Integer pageNo,Integer pageSize) {
        try{
        List<String> roleList = this.getRole(admin);
        for(int i=0; i<roleList.size(); i++){
            if(roleList.get(i).equals("11")){
                Integer current = pageNo;
                pageNo = (pageNo - 1) * pageSize;
                List<RoleList> RoleList = this.adminMapper.selectRoleList(pageNo,pageSize);
                JSONArray jsonObject = JSONArray.fromObject(RoleList);
                Integer total = this.adminMapper.selectRoleListCount();
                Integer totalPage = (total + pageSize - 1) / pageSize;
                JSONObject json = new JSONObject();
                json.put("status", "success");
                JSONObject data = new JSONObject();
                data.put("total", total);
                data.put("totalPage", totalPage);
                data.put("pageNo", current);
                data.put("pageSize", RoleList.size());
                JSONArray jsonArray = JSONArray.fromObject(jsonObject);
                data.put("record", jsonArray);
                json.put("data", data);
                String jsonStr = json.toString();
                return jsonStr;
            }
        }
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
    }

    @Override
    public String addRole(Admin admin, String idGroup,String account) {
        try{
            List<String> roleList = this.getRole(account);
            for(int i=0; i<roleList.size(); i++){
                if(roleList.get(i).equals("11")){
                    if(admin != null){
                        List<String> result = Arrays.asList(idGroup.split(","));

                        int a = this.adminMapper.insert(admin.getName(),admin.getDepartment(),admin.getPost(),
                                admin.getNo(),admin.getPassword(),account);
                        Admin adminNow = this.adminMapper.selectByAdmin(admin.getNo());
                        if(a>0){
                            for(int j=0; j<result.size(); j++){
                                AdminRole role = new AdminRole();
                                role.setAdminId(adminNow.getId());
                                role.setRoleId(Integer.parseInt(result.get(j)));
                                int b = this.adminRoleMapper.insert(adminNow.getId(),Integer.parseInt(result.get(j)),account);

                            }
                            JSONObject json = new JSONObject();
                            json.put("status", "success");
                            return json.toString();
                        }
                    }

                }
            }
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
    }

    @Override
    public String delete(String admin) {
        try{
            List<String> roleList = this.getRole(admin);
            for(int i=0; i<roleList.size(); i++){
                if(roleList.get(i).equals("11")){
                    this.adminMapper.deleteAll();
                    JSONObject json = new JSONObject();
                    json.put("status", "success");
                    return json.toString();
                }
            }
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
    }

    @Override
    public String updateRole(Admin admin, String idGroup, String account) {
        try{
            List<String> roleList = this.getRole(account);
            for(int i=0; i<roleList.size(); i++){
                if(roleList.get(i).equals("11")){
                    if(admin != null){
                        List<String> result = Arrays.asList(idGroup.split(","));
                        Admin adminNow = this.adminMapper.selectByPrimaryKey(admin.getId());
                        int a = this.adminMapper.updateByPrimaryKey(adminNow.getId(),admin.getName(),admin.getDepartment(),admin.getPost(),
                                admin.getNo(),admin.getPassword(),account);
                        ;
                        if(a>0){
                           int b = this.adminRoleMapper.deleteByAdminId(admin.getId());
                           if(b>0){
                               for(int j=0; j<result.size(); j++){
                                   AdminRole role = new AdminRole();
                                   role.setAdminId(adminNow.getId());
                                   role.setRoleId(Integer.parseInt(result.get(j)));
                                   int c = this.adminRoleMapper.insert(adminNow.getId(),Integer.parseInt(result.get(j)),account);

                               }
                           }
                            JSONObject json = new JSONObject();
                            json.put("status", "success");
                            return json.toString();
                        }
                    }

                }
            }
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
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
