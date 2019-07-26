package cn.teacherbe.service.impl;

import cn.teacherbe.dao.AccCommonInfoMapper;
import cn.teacherbe.dao.AccSeplenishmentMapper;
import cn.teacherbe.dao.WorkitemRelationMapper;
import cn.teacherbe.entity.*;
import cn.teacherbe.service.AccSeplenishmentService;
import cn.teacherbe.utils.DateUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("accSeplenishmentService")
public class AccSeplenishmentServiceImpl implements AccSeplenishmentService {

    @Autowired
    private AccSeplenishmentMapper accSeplenishmentMapper;

    @Autowired
    private AccCommonInfoMapper accCommonInfoMapper;

    @Autowired
    private WorkitemRelationMapper workitemRelationMapper;

    @Override
    public String getAccSeplenishmentInfo(Integer pageNo, Integer pageSize, String adminRole) {
        try {
            Integer current = pageNo;
            pageNo = (pageNo - 1) * pageSize;
            if (adminRole.equals("-1") || adminRole.equals("1")) {
                List<AccSeplenishmentInfo> AccSeplenishmentList = this.accSeplenishmentMapper.selectAll(pageNo, pageSize);
                JSONArray jsonObject = JSONArray.fromObject(AccSeplenishmentList);
                Integer total = this.accSeplenishmentMapper.selectAllCount();
                Integer totalPage = (total + pageSize - 1) / pageSize;
                JSONObject json = new JSONObject();
                json.put("status", "success");
                JSONObject data = new JSONObject();
                data.put("total", total);
                data.put("totalPage", totalPage);
                data.put("pageNo", current);
                data.put("pageSize", AccSeplenishmentList.size());
                JSONArray jsonArray = JSONArray.fromObject(jsonObject);
                data.put("record", jsonArray);
                json.put("data", data);
                String jsonStr = json.toString();
                return jsonStr;
            }
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        } catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 补料确认
     * */
    @Override
    public String seplenishmentConfirm(String idGroup,String admin,String adminRole) {
        JSONObject json = new JSONObject();
        try {
            //JSONObject json = JSONObject.fromObject(idGroup);

            if(adminRole.equals("-1")) {
                List<SeplenishmentConfirm1> SeplenishmentConfirm1 = com.alibaba.fastjson.JSONArray.parseArray(idGroup, SeplenishmentConfirm1.class);
                this.accSeplenishmentMapper.seplenishmentConfirm1(SeplenishmentConfirm1,admin);
                json.put("status","success");
                return json.toString();
            } else if(adminRole.equals("1")){
                List<SeplenishmentConfirm2> SeplenishmentConfirm2 = com.alibaba.fastjson.JSONArray.parseArray(idGroup, SeplenishmentConfirm2.class);
                //List<String> result = Arrays.asList(idGroup.split(","));
                this.accSeplenishmentMapper.seplenishmentConfirm2(SeplenishmentConfirm2,admin);
                this.accCommonInfoMapper.updateReplenishment(SeplenishmentConfirm2,admin);
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
                                                this.accCommonInfoMapper.updateById4(commonId,commonId2);

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
                                                this.accCommonInfoMapper.updateById4(commonId,commonId2);

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
                json.put("status","success");
                return json.toString();
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