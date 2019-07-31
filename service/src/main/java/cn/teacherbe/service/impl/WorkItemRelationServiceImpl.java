package cn.teacherbe.service.impl;

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

    @Override
    public String getPlan(Integer pageNo, Integer pageSize, String admin) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("11")) {
                    Integer current = pageNo;
                    pageNo = (pageNo - 1) * pageSize;
                    List<WorkitemRelationPlan> planList = this.workItemRealationMapper.selectPlan(pageNo, pageSize);
                    JSONArray jsonObject = JSONArray.fromObject(planList);
                    Integer total = this.workItemRealationMapper.selectPlanCount();
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
                JSONObject json = new JSONObject();
                json.put("status", "failed");
                return json.toString();
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
    public String underPlan(String idGroup, String kua, String admin) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("11")) {
                    JSONObject json = new JSONObject();
                    List<String> result = Arrays.asList(idGroup.split(","));
                    int status = this.workItemRealationMapper.underPlan(admin, result, kua);
                    if (status > 0) {
                        json.put("status", "success");
                        //statusMap.put("info","账号密码不能为空！");
                    } else {
                        json.put("status", "failed");
                    }
                    return json.toString();
                }
                JSONObject json = new JSONObject();
                json.put("status", "failed");
                return json.toString();
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
    public String getTaskInfo(Integer pageNo, Integer pageSize, String admin) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("11") || roleList.get(t).equals("4") || roleList.get(t).equals("5")
                        || roleList.get(t).equals("6") || roleList.get(t).equals("7")|| roleList.get(t).equals("8")
                        || roleList.get(t).equals("9")
                        || roleList.get(t).equals("10")
                        || roleList.get(t).equals("13")
                        || roleList.get(t).equals("14")){
                    Integer current = pageNo;
                    pageNo = (pageNo - 1) * pageSize;
                    List<TaskInfo> TaskList = this.workItemRealationMapper.selectTaskInfo(pageNo, pageSize, admin);
                    JSONArray jsonObject = JSONArray.fromObject(TaskList);
                    Integer total = this.workItemRealationMapper.selectTaskInfoCount(admin);
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
                JSONObject json = new JSONObject();
                json.put("status", "failed");
                return json.toString();
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
                if ( roleList.get(t).equals("7")|| roleList.get(t).equals("8")
                        || roleList.get(t).equals("9")
                        || roleList.get(t).equals("10")
                        || roleList.get(t).equals("13")
                        || roleList.get(t).equals("14")) {
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
                JSONObject json = new JSONObject();
                json.put("status", "failed");
                return json.toString();
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
    public String getAssemblyInfo(Integer pageNo, Integer pageSize, String admin) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("11") || roleList.get(t).equals("7") || roleList.get(t).equals("8")
                        || roleList.get(t).equals("9")
                        || roleList.get(t).equals("10")
                        || roleList.get(t).equals("13")
                        || roleList.get(t).equals("14")) {
                    Integer current = pageNo;
                    pageNo = (pageNo - 1) * pageSize;
                    List<WorkitemRelationPlan> AssemblyList = this.workItemRealationMapper.selectAssemblyInfo(pageNo, pageSize, admin);
                    JSONArray jsonObject = JSONArray.fromObject(AssemblyList);
                    Integer total = this.workItemRealationMapper.selectAssemblyInfoCount(admin);
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
                JSONObject json = new JSONObject();
                json.put("status", "failed");
                return json.toString();
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
                if (roleList.get(t).equals("7") || roleList.get(t).equals("8")
                        || roleList.get(t).equals("9")
                        || roleList.get(t).equals("10")
                        || roleList.get(t).equals("13")
                        || roleList.get(t).equals("14")) {
                    List<AssemblyInfo> AssemblyInfoList = com.alibaba.fastjson.JSONArray.parseArray(idGroup,
                            AssemblyInfo.class);
                    //List<String> result = Arrays.asList(idGroup.split(","));
                    this.workItemRealationMapper.assembly(AssemblyInfoList, admin);
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
    public String getWeldingInfo(Integer pageNo, Integer pageSize, String admin, Integer type) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("11") || roleList.get(t).equals("12") ) {
                    if (StringUtil.notEmpty(type)) {
                        Integer current = pageNo;
                        pageNo = (pageNo - 1) * pageSize;
                        List<WorkitemRelationPlan> WeldingInfoList = this.workItemRealationMapper.selectWeldingInfo(pageNo, pageSize, admin, type);
                        JSONArray jsonObject = JSONArray.fromObject(WeldingInfoList);
                        Integer total = this.workItemRealationMapper.selectWeldingInfoCount(admin, type);
                        Integer totalPage = (total + pageSize - 1) / pageSize;
                        JSONObject json = new JSONObject();
                        json.put("status", "success");
                        JSONObject data = new JSONObject();
                        data.put("total", total);
                        data.put("totalPage", totalPage);
                        data.put("pageNo", current);
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
                typeName = "平面校直";
            } else if(type == 2){
                typeName = "线性校直";
            } else if(type == 3){
                typeName = "焊接";
            } else if(type == 4){
                typeName = "线性焊透";
            }
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("12")) {
                    List<String> result = Arrays.asList(idGroup.split(","));

                    int status = this.workItemMapper.insert(admin,result,type,typeName);
                    //List<String> result = Arrays.asList(idGroup.split(","));
                    //this.workItemRealationMapper.assembly(AssemblyInfoList,admin);
                    for (int i = 0; i < result.size(); i++) {
                        List<Integer> idTeam = this.workItemMapper.selectProcess(Integer.parseInt(result.get(i)));
                        //WorkItem workItem = this.workItemMapper.selectByWorkId(Integer.parseInt(result.get(i)));
                        for (int j = 0; j < idTeam.size(); j++) {
                            List<WorkItem> workList = this.workItemMapper.selectByWrokId(Integer.parseInt(result.get(i)),idTeam.get(j));
                            if(workList == null || workList.isEmpty()){
                                json.put("status", "success");
                                return json.toString();
                            }
                        }
                            this.workItemRealationMapper.done(Integer.parseInt(result.get(i)), admin);
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
    public String getOutInfo(Integer pageNo, Integer pageSize, String admin) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("3") || roleList.get(t).equals("11")
                ) {
                    Integer current = pageNo;
                    pageNo = (pageNo - 1) * pageSize;
                    Integer newTotal = this.workItemRealationMapper.getTotal();
                    Integer done = this.workItemRealationMapper.getDone();
                    List<OutInfo> OutInfoList = this.workItemRealationMapper.selectOutInfo(pageNo, pageSize);
                    JSONArray jsonObject = JSONArray.fromObject(OutInfoList);
                    Integer total = this.workItemRealationMapper.selectOutInfoCount();
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
                JSONObject json = new JSONObject();
                json.put("status", "failed");
                return json.toString();
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
    public String getWeldingSelect(Integer pageNo, Integer pageSize, String admin) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("11") || roleList.get(t).equals("4") || roleList.get(t).equals("5")
                        || roleList.get(t).equals("6") || roleList.get(t).equals("7")|| roleList.get(t).equals("8")
                        || roleList.get(t).equals("9")
                        || roleList.get(t).equals("10")
                        || roleList.get(t).equals("13")
                        || roleList.get(t).equals("14")){
                    Integer current = pageNo;
                    pageNo = (pageNo - 1) * pageSize;
                    List<WorkItem> TaskList = this.workItemRealationMapper.getWeldingSelect(pageNo, pageSize);
                    JSONArray jsonObject = JSONArray.fromObject(TaskList);
                    Integer total = this.workItemRealationMapper.getWeldingSelectCount();
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
                JSONObject json = new JSONObject();
                json.put("status", "failed");
                return json.toString();
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
                if (roleList.get(t).equals("11") || roleList.get(t).equals("3")) {
                    List<String> result = Arrays.asList(idGroup.split(","));
                    this.workItemRealationMapper.out(result, carNumber,admin);
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
