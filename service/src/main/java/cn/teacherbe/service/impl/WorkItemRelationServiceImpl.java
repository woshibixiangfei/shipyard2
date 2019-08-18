package cn.teacherbe.service.impl;

import cn.teacherbe.dao.AccCommonInfoMapper;
import cn.teacherbe.dao.ShipSegmentationMapper;
import cn.teacherbe.dao.WorkItemMapper;
import cn.teacherbe.dao.WorkitemRelationMapper;
import cn.teacherbe.entity.*;
import cn.teacherbe.service.AdminService;
import cn.teacherbe.service.WorkItemRelationService;
import cn.teacherbe.utils.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service("workItemRealationService")
public class WorkItemRelationServiceImpl implements WorkItemRelationService {

    @Autowired
    private WorkitemRelationMapper workItemRealationMapper;
    @Autowired
    private WorkItemMapper workItemMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AccCommonInfoMapper accCommonInfoMapper;
    @Autowired
    private ShipSegmentationMapper shipSegmentationMapper;

    @Override
    public String getPlan(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("5")) {
                    Integer current = pageNo;
                    pageNo = (pageNo - 1) * pageSize;
                    List<WorkitemRelationPlan> planList = this.workItemRealationMapper.selectPlan(pageNo, pageSize,startDate,endDate);
                    JSONArray jsonObject = JSONArray.fromObject(planList);
                    Integer total = this.workItemRealationMapper.selectPlanCount(startDate,endDate);
                    Integer totalPage = (total + pageSize - 1) / pageSize;
                    JSONObject json = new JSONObject();
                    json.put("status", "success");
                    JSONObject data = new JSONObject();
                    data.put("total", total);
                    data.put("totalPage", totalPage);
                    data.put("pageNo", current);
                    data.put("pageSize", planList.size());
                    JSONArray jsonArray = JSONArray.fromObject(jsonObject);
                    data.put("record", jsonArray);
                    json.put("data", data);
                    String jsonStr = json.toString();
                    return jsonStr;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
        JSONObject json = new JSONObject();
        json.put("status", "failed");
        return json.toString();
    }

    @Override
    public String underPlan(String idGroup, String admin) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("5")) {
                    JSONObject json = new JSONObject();
                    List<UnderPlan> UnderPlan = com.alibaba.fastjson.JSONArray.parseArray(idGroup, UnderPlan.class);
                    int status = this.workItemRealationMapper.underPlan(admin, UnderPlan);
                    int status2 = this.workItemRealationMapper.underPlan2(admin, UnderPlan);
                    json.put("status", "success");
                    //statusMap.put("info","账号密码不能为空！");
                    return json.toString();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
        JSONObject json = new JSONObject();
        json.put("status", "failed");
        return json.toString();
    }

    @Override
    public String getTaskInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("6")){
                    Integer current = pageNo;
                    pageNo = (pageNo - 1) * pageSize;
                    List<TaskInfo> TaskList = this.workItemRealationMapper.selectTaskInfo(pageNo, pageSize, admin,startDate,endDate);
                    JSONArray jsonObject = JSONArray.fromObject(TaskList);
                    Integer total = this.workItemRealationMapper.selectTaskInfoCount(admin,startDate,endDate);
                    Integer totalPage = (total + pageSize - 1) / pageSize;
                    JSONObject json = new JSONObject();
                    json.put("status", "success");
                    JSONObject data = new JSONObject();
                    data.put("total", total);
                    data.put("totalPage", totalPage);
                    data.put("pageNo", current);
                    data.put("pageSize", TaskList.size());
                    JSONArray jsonArray = JSONArray.fromObject(jsonObject);
                    data.put("record", jsonArray);
                    json.put("data", data);
                    String jsonStr = json.toString();
                    return jsonStr;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
        JSONObject json = new JSONObject();
        json.put("status", "failed");
        return json.toString();
    }

    @Override
    public String claim(String admin, String idGroup) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if ( roleList.get(t).equals("6")) {
                    JSONObject json = new JSONObject();
                    List<String> result = Arrays.asList(idGroup.split(","));
                    int status = this.workItemRealationMapper.claim(result, admin);
                    if (status > 0) {
                        json.put("status", "success");
                        //statusMap.put("info","账号密码不能为空！");
                    } else {
                        json.put("status", "failed");
                    }
                    return json.toString();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
        JSONObject json = new JSONObject();
        json.put("status", "failed");
        return json.toString();
    }

    @Override
    public String getAssemblyInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("7")) {
                    Integer current = pageNo;
                    pageNo = (pageNo - 1) * pageSize;
                    List<WorkitemRelationPlan> AssemblyList = this.workItemRealationMapper.selectAssemblyInfo(pageNo, pageSize, admin,startDate,endDate);
                    JSONArray jsonObject = JSONArray.fromObject(AssemblyList);
                    Integer total = this.workItemRealationMapper.selectAssemblyInfoCount(admin,startDate,endDate);
                    Integer totalPage = (total + pageSize - 1) / pageSize;
                    JSONObject json = new JSONObject();
                    json.put("status", "success");
                    JSONObject data = new JSONObject();
                    data.put("total", total);
                    data.put("totalPage", totalPage);
                    data.put("pageNo", current);
                    data.put("pageSize", AssemblyList.size());
                    JSONArray jsonArray = JSONArray.fromObject(jsonObject);
                    data.put("record", jsonArray);
                    json.put("data", data);
                    String jsonStr = json.toString();
                    return jsonStr;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
        JSONObject json = new JSONObject();
        json.put("status", "failed");
        return json.toString();
    }

    @Override
    public String assembly(String idGroup, String admin) {
        JSONObject json = new JSONObject();
        try {
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("7")) {
                    List<AssemblyInfo> AssemblyInfoList = com.alibaba.fastjson.JSONArray.parseArray(idGroup,
                            AssemblyInfo.class);
                    for(int j=0; j<roleList.size(); j++) {
                        if(roleList.get(j).equals("17")) {
                            for (int i = 0; i < AssemblyInfoList.size(); i++) {
                                if(AssemblyInfoList.get(i).getProcess().indexOf("2") != -1){
                                    this.workItemRealationMapper.insertDaSB(AssemblyInfoList.get(i).getId());
                                }

                            }
                        }

                    }
                    //List<String> result = Arrays.asList(idGroup.split(","));
                    this.workItemRealationMapper.assembly(AssemblyInfoList, admin);
                    for(int c=0; c<roleList.size(); c++) {
                        if (roleList.get(c).equals("17")){
                            this.workItemRealationMapper.assembly2(AssemblyInfoList);
                        }
                        for (int b = 0; b < AssemblyInfoList.size(); b++) {
                            if(AssemblyInfoList.get(b).getProcess().equals("2")){
                                this.workItemRealationMapper.done(AssemblyInfoList.get(b).getId(),admin);
                            }
                        }
                    }
                    json.put("status", "success");
                    return json.toString();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            json.put("status","failed");
            return json.toString();
        }
        json.put("status","failed");
        return json.toString();
    }

    @Override
    public String getWeldingInfo(Integer pageNo, Integer pageSize, String admin, Integer type,String startDate,String endDate) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList != null) {
                    if (StringUtil.notEmpty(type)) {
                        Integer current = pageNo;
                        pageNo = (pageNo - 1) * pageSize;
                        List<WorkitemRelationPlan> WeldingInfoList = this.workItemRealationMapper.selectWeldingInfo(pageNo, pageSize, admin, type,startDate,endDate);
                        JSONArray jsonObject = JSONArray.fromObject(WeldingInfoList);
                        Integer total = this.workItemRealationMapper.selectWeldingInfoCount(admin, type,startDate,endDate);
                        Integer totalPage = (total + pageSize - 1) / pageSize;
                        Integer day = this.workItemRealationMapper.getWelding12345Day(type);
                        Integer month = this.workItemRealationMapper.getWelding12345Month(type);
                        JSONObject json = new JSONObject();
                        json.put("status", "success");
                        JSONObject data = new JSONObject();
                        data.put("total", total);
                        data.put("totalPage", totalPage);
                        data.put("pageNo", current);
                        data.put("day", day);
                        data.put("month", month);
                        data.put("pageSize", WeldingInfoList.size());
                        JSONArray jsonArray = JSONArray.fromObject(jsonObject);
                        data.put("record", jsonArray);
                        json.put("data", data);
                        String jsonStr = json.toString();
                        return jsonStr;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
        JSONObject json = new JSONObject();
        json.put("status", "failed");
        return json.toString();
    }

    @Override
    public String welding(String idGroup, String admin, Integer type) {
        JSONObject json = new JSONObject();
        try {
            List<String> roleList = this.adminService.getRole(admin);
            String typeName = null;
            if(type == 1){
                typeName = "手工焊接";
            } else if(type == 2){
                typeName = "线型焊接";
            } else if(type == 3){
                typeName = "平直焊接";
            } else if(type == 4){
                typeName = "平直校直";
            } else if(type == 5){
                typeName = "平直焊透";
            } else if(type == 6){
                typeName = "线型校直";
            } else if(type == 7){
                typeName = "线型焊透";
            }
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList != null) {
                    List<String> result = Arrays.asList(idGroup.split(","));

                    int status = this.workItemMapper.insert(admin,result,type,typeName);
                    //List<String> result = Arrays.asList(idGroup.split(","));
                    //this.workItemRealationMapper.assembly(AssemblyInfoList,admin);
                    for (int i = 0; i < result.size(); i++) {
                        List<Integer> idTeam = this.workItemMapper.selectProcess(Integer.parseInt(result.get(i)));
                        boolean dasabi = true;
                        //WorkItem workItem = this.workItemMapper.selectByWorkId(Integer.parseInt(result.get(i)));
                        for (int j = 0; j < idTeam.size(); j++) {
                            if(idTeam.get(j) == 0){
                                continue;
                            }
                            List<WorkItem> workList = this.workItemMapper.selectByWrokId(Integer.parseInt(result.get(i)),idTeam.get(j));
                            if(workList == null || workList.isEmpty()){
                                dasabi = false;
                            }
                        }
                        if(dasabi == true){
                            this.workItemRealationMapper.done(Integer.parseInt(result.get(i)), admin);
                        }

                        int a = this.workItemRealationMapper.a(Integer.parseInt(result.get(i)));
                        int b = this.workItemRealationMapper.b(Integer.parseInt(result.get(i)));
                        if(a == b){
                            this.workItemRealationMapper.c(Integer.parseInt(result.get(i)),admin);
                        }
                    }
                    json.put("status", "success");
                    return json.toString();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            json.put("status","failed");
            return json.toString();
        }
        json.put("status","failed");
        return json.toString();
    }

    @Override
    public String getOutInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("16")) {
                    Integer current = pageNo;
                    pageNo = (pageNo - 1) * pageSize;
                    Integer newTotal = this.workItemRealationMapper.getTotal();
                    Integer done = this.workItemRealationMapper.getDone();
                    List<OutInfo> OutInfoList = this.workItemRealationMapper.selectOutInfo(pageNo, pageSize,startDate,endDate);
                    JSONArray jsonObject = JSONArray.fromObject(OutInfoList);
                    Integer total = this.workItemRealationMapper.selectOutInfoCount(startDate,endDate);
                    Integer totalPage = (total + pageSize - 1) / pageSize;
                    JSONObject json = new JSONObject();
                    json.put("status", "success");
                    JSONObject data = new JSONObject();
                    data.put("total", total);
                    data.put("totalPage", totalPage);
                    data.put("pageNo", current);
                    data.put("pageSize", OutInfoList.size());
                    if(newTotal == 0) {
                        data.put("percent", 0);
                    } else {
                        double f1 = new BigDecimal((float)done / newTotal).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        data.put("percent", f1);
                    }
                    JSONArray jsonArray = JSONArray.fromObject(jsonObject);
                    data.put("record", jsonArray);
                    json.put("data", data);
                    String jsonStr = json.toString();
                    return jsonStr;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
        JSONObject json = new JSONObject();
        json.put("status", "failed");
        return json.toString();
    }

    @Override
    public String getWeldingSelect(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList != null){
                    Integer current = pageNo;
                    pageNo = (pageNo - 1) * pageSize;
                    List<WorkItem> TaskList = this.workItemRealationMapper.getWeldingSelect(pageNo, pageSize,startDate,endDate);
                    JSONArray jsonObject = JSONArray.fromObject(TaskList);
                    Integer total = this.workItemRealationMapper.getWeldingSelectCount(startDate,endDate);
                    Integer all = this.workItemRealationMapper.getWeldingAll();
                    Integer totalPage = (total + pageSize - 1) / pageSize;
                    JSONObject json = new JSONObject();
                    json.put("status", "success");
                    JSONObject data = new JSONObject();
                    data.put("total", total);
                    data.put("totalPage", totalPage);
                    data.put("pageNo", current);
                    data.put("pageSize", TaskList.size());
                    data.put("all", all);
                    JSONArray jsonArray = JSONArray.fromObject(jsonObject);
                    data.put("record", jsonArray);
                    json.put("data", data);
                    String jsonStr = json.toString();
                    return jsonStr;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
        JSONObject json = new JSONObject();
        json.put("status", "failed");
        return json.toString();
    }

    @Override
    public String out(String idGroup, String admin, String carNumber) {
        JSONObject json = new JSONObject();
        try {
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("16")) {
                    List<String> result = Arrays.asList(idGroup.split(","));
                    this.workItemRealationMapper.out(result, carNumber,admin);
                    for(int i=0; i<result.size(); i++){
                        List<FuckYou> fuckYou = this.workItemRealationMapper.selectFuckyou(Integer.parseInt(result.get(i)));
                        this.shipSegmentationMapper.updateFuckyou(fuckYou.get(0).getShipNumber(),fuckYou.get(0).getSegmentation());
                    }
                    json.put("status", "success");
                    return json.toString();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            json.put("status","failed");
            return json.toString();
        }
        json.put("status","failed");
        return json.toString();
    }


}
