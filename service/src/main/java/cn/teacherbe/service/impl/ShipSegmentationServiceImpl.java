package cn.teacherbe.service.impl;

import cn.teacherbe.dao.AccCommonInfoMapper;
import cn.teacherbe.dao.ShipSegmentationMapper;
import cn.teacherbe.entity.Segmentation;
import cn.teacherbe.entity.ShipSegmentation;
import cn.teacherbe.entity.ShipSegmentation2;
import cn.teacherbe.service.ShipSegmentationService;
import cn.teacherbe.utils.DateUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("shipSegmentationService")
public class ShipSegmentationServiceImpl implements ShipSegmentationService {

    @Autowired
    private ShipSegmentationMapper shipSegmentationMapper;
    @Autowired
    private AccCommonInfoMapper accCommonInfoMapper;
    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 添加分段信息
     * */
    @Override
    public boolean createShipSegmentation(String shipNumber, String admin,Integer size) {
        String dateNow = new DateUtils().dateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
        Date nowDate = new DateUtils().stringtoDate(dateNow,"yyyy-MM-dd HH:mm:ss");
        List<Segmentation> segmentationList = this.shipSegmentationMapper.querySegmentation(shipNumber);
        if(segmentationList != null && !segmentationList.isEmpty()){
            ShipSegmentation shipSegmentation = new ShipSegmentation();

            for(Segmentation segmentation : segmentationList){
                shipSegmentation.setShipId(segmentation.getShipId());
                shipSegmentation.setSegmentation(String.valueOf(new Double(segmentation.getSegmentation()).intValue()));
                size = this.accCommonInfoMapper.selectAll3Count(shipNumber,String.valueOf(new Double(segmentation.getSegmentation()).intValue()));
                shipSegmentation.setCreator(admin);
                shipSegmentation.setUpdater(admin);
                shipSegmentation.setDeleteFlag(0);
                shipSegmentation.setStatus(0);
                shipSegmentation.setNumber(size);
                shipSegmentation.setRegisterDate(dateNow);
                shipSegmentation.setUpdateDate(dateNow);
                int createSegmentationStatus = this.shipSegmentationMapper.insert(shipSegmentation);
                if(createSegmentationStatus != 1){
                    continue;
                }
            }
            return true;
        }
        return false;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取分段
     * */
    @Override
    public String getShipSegmentation(String shipNumber, Integer pageNo, Integer pageSize) {
        try {
        Integer current = pageNo;
        pageNo = (pageNo - 1)*pageSize;
        List<ShipSegmentation2> shipSegmentationList = this.shipSegmentationMapper.selectAll(shipNumber,pageNo,pageSize);
        Integer total = this.shipSegmentationMapper.selectAllCount(shipNumber);
        Integer totalPage = (total + pageSize - 1) / pageSize;
        JSONObject json = new JSONObject();
        json.put("status","success");
        JSONObject data = new JSONObject();
        data.put("total",total);
        data.put("totalPage",totalPage);
        data.put("pageNo",current);
        data.put("pageSize",shipSegmentationList.size());
        JSONArray jsonArray = JSONArray.fromObject(shipSegmentationList);
        data.put("record",jsonArray);
        json.put("data",data);
        String jsonStr = json.toString();
        return jsonStr;
        }catch (Exception e){
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("status", "failed");
            return json.toString();
        }
    }
}
