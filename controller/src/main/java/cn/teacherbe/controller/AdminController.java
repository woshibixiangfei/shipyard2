package cn.teacherbe.controller;

import cn.teacherbe.entity.AccCommonInfo;
import cn.teacherbe.entity.Admin;
import cn.teacherbe.service.AccCommonInfoService;
import cn.teacherbe.service.AdminService;
import cn.teacherbe.service.ShipNumberService;
import cn.teacherbe.service.ShipSegmentationService;
import cn.teacherbe.utils.DateUtils;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController("adminController")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ShipNumberService shipNumberService;
    @Autowired
    private AccCommonInfoService accCommonInfoService;
    @Autowired
    private ShipSegmentationService shipSegmentationService;

    @RequestMapping(value = "/login")
    public JSONObject login(Admin admin){
        JSONObject loginMap = this.adminService.login(admin);
        return loginMap;
    }

    @RequestMapping(value = "/getRole")
    public List<String> getRole(String admin){
        List<String> roleList = this.adminService.getRole(admin);
        return roleList;
    }

    @RequestMapping(value = "/getRoleList")
    public String getRoleList(String admin,Integer pageNo,Integer pageSize){
        String roleList = this.adminService.getRoleList(admin,pageNo,pageSize);
        return roleList;
    }

    @RequestMapping(value = "/addRole")
    public String addRole(Admin admin,String idGroup,String account){
        String roleList = this.adminService.addRole(admin,idGroup,account);
        return roleList;
    }

    @RequestMapping(value = "/updateRole")
    public String updateRole(Admin admin,String idGroup,String account){
        String roleList = this.adminService.updateRole(admin,idGroup,account);
        return roleList;
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public JSONObject upload(HttpServletRequest request,String admin) throws Exception {
        try {
            JSONObject statusMap = new JSONObject();
            List<String> roleList = this.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("11")) {
                    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

                    MultipartFile file = multipartRequest.getFile("filename");
                    if (file.isEmpty()) {
                        statusMap.put("status", "failed");
                        return statusMap;
                    }
                    InputStream inputStream = file.getInputStream();
                    Workbook work = adminService.getWorkbook(inputStream, file.getOriginalFilename());
                    String shipNumber = work.getSheetName(0);
                    inputStream.close();
                    InputStream inputStream2 = file.getInputStream();
                    List<List<Object>> list = adminService.getBankListByExcel(inputStream2, file.getOriginalFilename());
                    inputStream2.close();
                    boolean createShipStatus = this.shipNumberService.createShipNumber(shipNumber, admin);
                    if (createShipStatus == false) {
                        statusMap.put("status", "failed");
                        return statusMap;
                    }
                    AccCommonInfo accCommonInfo = new AccCommonInfo();
                    String dateNow = new DateUtils().dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
                    Date nowDate = new DateUtils().stringtoDate(dateNow, "yyyy-MM-dd HH:mm:ss");
                    for (int i = 2; i < list.size(); i++) {
                        List<Object> lo = list.get(i);
                        if(list.get(i).size() < 8){
                            break;
                        }
                        int number = new Double(list.get(i).get(8).toString()).intValue();
                        for (int j = 0; j < number; j++) {
                            accCommonInfo.setShipNumber(shipNumber);
                            accCommonInfo.setSerialNumber(new Double(list.get(i).get(0).toString()).intValue());
                            accCommonInfo.setBatch(new Double(list.get(i).get(1).toString()).intValue());
                            accCommonInfo.setSegmentation(String.valueOf(new Double(list.get(i).get(2).toString()).intValue()));
                            accCommonInfo.setPartNumber(list.get(i).get(3).toString());
                            accCommonInfo.setThickness(new Double(list.get(i).get(4).toString()).intValue());
                            accCommonInfo.setWidth(new Double(list.get(i).get(5).toString()).intValue());
                            accCommonInfo.setLength(new Double(list.get(i).get(6).toString()).intValue());
                            accCommonInfo.setType(list.get(i).get(7).toString());
                            accCommonInfo.setNumberParts(1);
                            accCommonInfo.setGroup(list.get(i).get(9).toString());
                            accCommonInfo.setStatus(0);
                            accCommonInfo.setReplenishmentStatus(0);
                            accCommonInfo.setCreator(admin);
                            accCommonInfo.setUpdater(admin);
                            accCommonInfo.setDeleteFlag(0);
                            accCommonInfo.setRegisterDate(dateNow);
                            accCommonInfo.setUpdateDate(dateNow);
                            int createStatus = this.accCommonInfoService.insert(accCommonInfo);
                            if (createStatus != 1) {
                                break;
                            }
                        }
                        //TODO 随意发挥


                    }
                    boolean createSegmentationStatus = this.shipSegmentationService.createShipSegmentation(shipNumber, admin);
                    if (createSegmentationStatus == false) {
                        statusMap.put("status", "failed");
                        return statusMap;
                    }
                    System.out.println("完成");
                    statusMap.put("status", "success");
                    return statusMap;
                } else {
                    statusMap.put("status", "failed");
                    return statusMap;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json;
        }
        JSONObject json = new JSONObject();
        json.put("status", "failed");
        return json;
    }

}
