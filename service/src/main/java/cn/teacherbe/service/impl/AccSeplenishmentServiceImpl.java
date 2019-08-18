package cn.teacherbe.service.impl;

import cn.teacherbe.dao.AccCommonInfoMapper;
import cn.teacherbe.dao.AccSeplenishmentMapper;
import cn.teacherbe.dao.WorkitemRelationMapper;
import cn.teacherbe.entity.*;
import cn.teacherbe.service.AccSeplenishmentService;
import cn.teacherbe.service.AdminService;
import cn.teacherbe.utils.DateUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("accSeplenishmentService")
public class AccSeplenishmentServiceImpl implements AccSeplenishmentService {

    @Autowired
    private AccSeplenishmentMapper accSeplenishmentMapper;

    @Autowired
    private AccCommonInfoMapper accCommonInfoMapper;

    @Autowired
    private WorkitemRelationMapper workitemRelationMapper;

    @Autowired
    private cn.teacherbe.service.AdminService adminService;
    @Override
    public String getAccSeplenishmentInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate) {
        try {
            Integer current = pageNo;
            pageNo = (pageNo - 1) * pageSize;
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("2") || roleList.get(t).equals("3")) {
                    List<AccSeplenishmentInfo> AccSeplenishmentList = this.accSeplenishmentMapper.selectAll(pageNo, pageSize,startDate,endDate);
                    JSONArray jsonObject = JSONArray.fromObject(AccSeplenishmentList);
                    Integer total = this.accSeplenishmentMapper.selectAllCount(startDate,endDate);
                    Integer newTotal = this.accCommonInfoMapper.getTotal();
                    Integer done = this.accCommonInfoMapper.getDone();
                    Integer unDone = this.accCommonInfoMapper.getUnDone();
                    Integer totalPage = (total + pageSize - 1) / pageSize;
                    JSONObject json = new JSONObject();
                    json.put("status", "success");
                    JSONObject data = new JSONObject();
                    data.put("total", total);
                    data.put("totalPage", totalPage);
                    data.put("pageNo", current);
                    data.put("pageSize", AccSeplenishmentList.size());
                    data.put("newTotal", newTotal);
                    data.put("done", done);
                    data.put("unDone", unDone);
                    JSONArray jsonArray = JSONArray.fromObject(jsonObject);
                    data.put("record", jsonArray);
                    json.put("data", data);
                    String jsonStr = json.toString();
                    return jsonStr;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
        JSONObject json = new JSONObject();
        json.put("status", "failed");
        return json.toString();
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 补料确认
     * */
    @Override
    public String seplenishmentConfirm(String idGroup,String admin) {
        JSONObject json = new JSONObject();
        try {
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("2")) {
                    List<SeplenishmentConfirm1> SeplenishmentConfirm1 = com.alibaba.fastjson.JSONArray.parseArray(idGroup, SeplenishmentConfirm1.class);
                    if(SeplenishmentConfirm1.get(0).getReplenishmentStatus() == null){
                        continue;
                    }
                    this.accSeplenishmentMapper.seplenishmentConfirm1(SeplenishmentConfirm1, admin);
                    json.put("status", "success");
                    return json.toString();
                } else if (roleList.get(t).equals("3")) {
                    List<SeplenishmentConfirm2> SeplenishmentConfirm2 = com.alibaba.fastjson.JSONArray.parseArray(idGroup, SeplenishmentConfirm2.class);
                    //List<String> result = Arrays.asList(idGroup.split(","));
                    if(SeplenishmentConfirm2.get(0).getArrivalStatus() == null){
                        continue;
                    }
                    this.accSeplenishmentMapper.seplenishmentConfirm2(SeplenishmentConfirm2, admin);
                    this.accCommonInfoMapper.updateReplenishment(SeplenishmentConfirm2, admin);
                    List<PairEntity> unPairList1 = this.accSeplenishmentMapper.selectUnPair(SeplenishmentConfirm2);
                    List<PairEntity> unPairList2 = this.accCommonInfoMapper.selectUnPair();
                    WorkitemRelation workitemRelation = new WorkitemRelation();
                    String dateNow = new DateUtils().dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
                    for (int i = 0; i < unPairList1.size(); i++) {
                        Integer commonId = unPairList1.get(i).getId();
                        String partNumber = unPairList1.get(i).getPartNumber();
                        for (int j = 0; j < unPairList2.size(); j++) {
                            Integer commonId2 = unPairList2.get(j).getId();
                            String partNumber2 = unPairList2.get(j).getPartNumber();
                            if (commonId != commonId2) {
                                if (partNumber.length() == partNumber2.length()) {
                                    if (partNumber.substring(0, partNumber.length() - 1).equals(partNumber2.substring(0, partNumber2.length() - 1))) {
                                        if (partNumber.substring(partNumber.length() - 1, partNumber.length()).equals("F")) {
                                            if (partNumber2.substring(partNumber2.length() - 1, partNumber2.length()).equals("W")) {
                                                // System.out.println(a + "/" + b);
                                                List<WorkitemRelation> pairList = this.workitemRelationMapper.selectByCommonId(commonId);
                                                List<WorkitemRelation> pairList2 = this.workitemRelationMapper.selectByCommonId2(commonId2);
                                                List<WorkitemRelation> pairList3 = this.workitemRelationMapper.selectByCommonId(commonId2);
                                                List<WorkitemRelation> pairList4 = this.workitemRelationMapper.selectByCommonId2(commonId);
                                                if (pairList.size() == 0 && pairList2.size() == 0 && pairList3.size() == 0 && pairList4.size() == 0) {
                                                    workitemRelation.setOneCommonId(commonId);
                                                    workitemRelation.setTwoCommonId(commonId2);
                                                    workitemRelation.setExecutionCross("nothing");
                                                    workitemRelation.setExecutiveClass("nothing");
                                                    workitemRelation.setCheckIf(0);
                                                    workitemRelation.setProcess("nothing");
                                                    workitemRelation.setWorkitemStatus(0);
                                                    workitemRelation.setCreator(admin);
                                                    workitemRelation.setRegisterDate(dateNow);
                                                    workitemRelation.setUpdater(admin);
                                                    workitemRelation.setUpdateDate(dateNow);
                                                    this.workitemRelationMapper.insertSelective(workitemRelation);
                                                    this.accCommonInfoMapper.updateById4(commonId, commonId2);

                                                } else {
                                                    continue;
                                                }
                                            }
                                        } else if (partNumber.substring(partNumber.length() - 1, partNumber.length()).equals("W")) {
                                            if (partNumber2.substring(partNumber2.length() - 1, partNumber2.length()).equals("F")) {
                                                // System.out.println(a + "/" + b);
                                                List<WorkitemRelation> pairList = this.workitemRelationMapper.selectByCommonId(commonId);
                                                List<WorkitemRelation> pairList2 = this.workitemRelationMapper.selectByCommonId2(commonId2);
                                                List<WorkitemRelation> pairList3 = this.workitemRelationMapper.selectByCommonId(commonId2);
                                                List<WorkitemRelation> pairList4 = this.workitemRelationMapper.selectByCommonId2(commonId);
                                                if (pairList.size() == 0 && pairList2.size() == 0 && pairList3.size() == 0 && pairList4.size() == 0) {
                                                    workitemRelation.setOneCommonId(commonId);
                                                    workitemRelation.setTwoCommonId(commonId2);
                                                    workitemRelation.setExecutionCross("nothing");
                                                    workitemRelation.setExecutiveClass("nothing");
                                                    workitemRelation.setCheckIf(0);
                                                    workitemRelation.setProcess("nothing");
                                                    workitemRelation.setWorkitemStatus(0);
                                                    workitemRelation.setCreator(admin);
                                                    workitemRelation.setRegisterDate(dateNow);
                                                    workitemRelation.setUpdater(admin);
                                                    workitemRelation.setUpdateDate(dateNow);
                                                    this.workitemRelationMapper.insertSelective(workitemRelation);
                                                    this.accCommonInfoMapper.updateById4(commonId, commonId2);

                                                } else {
                                                    continue;
                                                }
                                            }
                                        }
                                        // System.out.println(1);
                                    } else {
                                        //System.out.println(2);
                                    }
                                } else {
                                    continue;
                                }
                            } else {
                                continue;
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
    public String getLajiInfo(Integer pageNo, Integer pageSize, String admin) {
        try {
            Integer current = pageNo;
            pageNo = (pageNo - 1) * pageSize;
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("2") || roleList.get(t).equals("3")) {
                    List<AccCommonInfo> AccInecomingInfoList = this.accCommonInfoMapper.selectLajiInfo(pageNo, pageSize);
                    JSONArray jsonObject = JSONArray.fromObject(AccInecomingInfoList);
                    Integer total = this.accCommonInfoMapper.selectLajiInfoCount();
                    Integer totalPage = (total + pageSize - 1) / pageSize;
                    JSONObject json = new JSONObject();
                    json.put("status", "success");
                    JSONObject data = new JSONObject();
                    data.put("total", total);
                    data.put("totalPage", totalPage);
                    data.put("pageNo", current);
                    data.put("pageSize", AccInecomingInfoList.size());
                    JSONArray jsonArray = JSONArray.fromObject(jsonObject);
                    data.put("record", jsonArray);
                    json.put("data", data);
                    String jsonStr = json.toString();
                    return jsonStr;
                }
            }
        } catch (Exception e){
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
    public String laji(String idGroup, String admin) {
        try{
            List<String> roleList = this.adminService.getRole(admin);
            for (int t = 0; t < roleList.size(); t++) {
                if (roleList.get(t).equals("3")) {
                    List<String> result = Arrays.asList(idGroup.split(","));
                    List<Text> text = new ArrayList<Text>();
                    for(int i=0; i<result.size(); i++){
                        Text text1 = new Text();
                        text1.setId(Integer.parseInt(result.get(i)));
                        text.add(text1);
                    }
                    JSONObject json = new JSONObject();
                    String dateNow = new DateUtils().dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
                    int status = this.accCommonInfoMapper.laji(text,admin);
                    this.accCommonInfoMapper.sb(admin,dateNow,result);
                    List<String> caonima = this.accCommonInfoMapper.selectCaonimaInfo();
                    this.accSeplenishmentMapper.insertInit(caonima, admin, dateNow, admin, dateNow);
                    this.accCommonInfoMapper.updateById2(admin,dateNow,caonima);

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
}
