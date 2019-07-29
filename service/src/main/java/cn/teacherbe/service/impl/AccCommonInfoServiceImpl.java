package cn.teacherbe.service.impl;

import cn.teacherbe.dao.AccCommonInfoMapper;
import cn.teacherbe.dao.AccSeplenishmentMapper;
import cn.teacherbe.dao.WorkitemRelationMapper;
import cn.teacherbe.entity.AccCommonInfo;
import cn.teacherbe.entity.AccInecomingInfo;
import cn.teacherbe.entity.WorkitemRelation;
import cn.teacherbe.service.AccCommonInfoService;
import cn.teacherbe.utils.DateUtils;
import cn.teacherbe.utils.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("accCommonInfoService")
public class AccCommonInfoServiceImpl implements AccCommonInfoService {

    @Autowired
    private AccCommonInfoMapper accCommonInfoMapper;

    @Autowired
    private AccSeplenishmentMapper accSeplenishmentMapper;

    @Autowired
    private WorkitemRelationMapper workitemRelationMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(AccCommonInfo record) {
        return this.accCommonInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(AccCommonInfo record) {
        return 0;
    }

    @Override
    public AccCommonInfo selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(AccCommonInfo record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(AccCommonInfo record) {
        return 0;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取物料信息
     * */
    @Override
    public String getAccCommonInfo(String shipNumber, String segmentation, Integer pageNo, Integer pageSize, String adminRole) {
        try{
            if(adminRole.equals("-1")) {
                Integer current = pageNo;
                pageNo = (pageNo - 1) * pageSize;
                List<AccCommonInfo> accCommonInfoList = this.accCommonInfoMapper.selectAll(0, shipNumber, segmentation, pageNo, pageSize);
                JSONArray jsonObject = JSONArray.fromObject(accCommonInfoList);
                Integer total = this.accCommonInfoMapper.selectAllCount(0, shipNumber, segmentation);
                Integer totalPage = (total + pageSize - 1) / pageSize;
                JSONObject json = new JSONObject();
                json.put("status", "success");
                JSONObject data = new JSONObject();
                data.put("total", total);
                data.put("totalPage", totalPage);
                data.put("pageNo", current);
                data.put("pageSize", accCommonInfoList.size());
                JSONArray jsonArray = JSONArray.fromObject(jsonObject);
                data.put("record", jsonArray);
                json.put("data", data);
                String jsonStr = json.toString();
                return jsonStr;
            } else {
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
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取来料信息
     * */
    @Override
    public String getAccInecoming(Integer pageNo, Integer pageSize, String adminRole) {
        try{
            if(adminRole.equals("1")){
                Integer current = pageNo;
                pageNo = (pageNo - 1) * pageSize;
                List<AccInecomingInfo> accInecomingInfoList = this.accCommonInfoMapper.selectAccInecomingInfo(pageNo,pageSize);
                JSONArray jsonObject = JSONArray.fromObject(accInecomingInfoList);
                Integer total = this.accCommonInfoMapper.selectAccInecomingInfoCount();
                Integer totalPage = (total + pageSize - 1) / pageSize;
                JSONObject json = new JSONObject();
                json.put("status", "success");
                JSONObject data = new JSONObject();
                data.put("total", total);
                data.put("totalPage", totalPage);
                data.put("pageNo", current);
                data.put("pageSize", accInecomingInfoList.size());
                JSONArray jsonArray = JSONArray.fromObject(jsonObject);
                data.put("record", jsonArray);
                json.put("data", data);
                String jsonStr = json.toString();
                return jsonStr;
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

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 生成发货单
     * */
    @Override
    public String generateInvoice(String idGroup, String admin, String adminRole) {
        if(adminRole.equals("-1") && StringUtil.notEmpty(idGroup)){
            List<String> result = Arrays.asList(idGroup.split(","));
            JSONObject json = new JSONObject();
            String dateNow = new DateUtils().dateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
            int status = this.accCommonInfoMapper.updateById(admin,dateNow,result);
            if(status > 0){
                json.put("status","success");
                //statusMap.put("info","账号密码不能为空！");
            } else {
                json.put("status","failed");
            }
            return json.toString();
        }
        JSONObject json = new JSONObject();
        json.put("status", "failed");
        return json.toString();
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取发货单信息
     * */
    @Override
    public String getInvoiceInfo(Integer pageNo, Integer pageSize, String adminRole) {
        try{
        if(adminRole.equals("-1")){
            Integer current = pageNo;
            pageNo = (pageNo - 1) * pageSize;
            List<AccCommonInfo> InvoiceInfoList = this.accCommonInfoMapper.selectInvoiceInfo(1,pageNo,pageSize);
            JSONArray jsonObject = JSONArray.fromObject(InvoiceInfoList);
            Integer total = this.accCommonInfoMapper.selectInvoiceInfoCount(1);
            Integer totalPage = (total + pageSize - 1) / pageSize;
            JSONObject json = new JSONObject();
            json.put("status", "success");
            JSONObject data = new JSONObject();
            data.put("total", total);
            data.put("totalPage", totalPage);
            data.put("pageNo", current);
            data.put("pageSize", InvoiceInfoList.size());
            JSONArray jsonArray = JSONArray.fromObject(jsonObject);
            data.put("record", jsonArray);
            json.put("data", data);
            String jsonStr = json.toString();
            return jsonStr;
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

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 发货
     * */
    @Override
    public String ship(String idGroup, String admin, String adminRole) {
        JSONObject json = new JSONObject();
        try {
            String dateNow = new DateUtils().dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");

            if (adminRole.equals("-1")) {
                this.accCommonInfoMapper.updateById3(admin, dateNow);
                List<String> result = Arrays.asList(idGroup.split(","));
                if(!StringUtil.notEmpty(idGroup)) {
                    this.accCommonInfoMapper.updateById2(admin, dateNow, result);
                    this.accSeplenishmentMapper.insertInit(result, admin, dateNow, admin, dateNow);
                }
                List<AccCommonInfo> AccCommonInfoPairList = this.accCommonInfoMapper.selectPair();
                List<AccCommonInfo> AccCommonInfoPairList2 = AccCommonInfoPairList;
                WorkitemRelation workitemRelation = new WorkitemRelation();
                for (int i = 0; i < AccCommonInfoPairList.size(); i++) {
                    Integer commonId = AccCommonInfoPairList.get(i).getId();
                    String partNumber = AccCommonInfoPairList.get(i).getPartNumber();
                    for (int j = 0; j < AccCommonInfoPairList2.size(); j++) {
                        Integer commonId2 = AccCommonInfoPairList.get(j).getId();
                        String partNumber2 = AccCommonInfoPairList.get(j).getPartNumber();
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
            }
        } catch (Exception e){
            e.printStackTrace();
            json.put("status","failed");
            return json.toString();
        }
        json.put("status","success");
        return json.toString();
    }

}
