package cn.teacherbe.service.impl;

import cn.teacherbe.dao.ShipNumberMapper;
import cn.teacherbe.entity.ShipNumber;
import cn.teacherbe.service.ShipNumberService;
import cn.teacherbe.utils.DateUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("shipNumberService")
public class ShipNumberServiceImpl implements ShipNumberService {

    @Autowired
    private ShipNumberMapper shipNumberMapper;

    /*
    * @author 毕翔斐
    * @version 1.0
    * @description 添加船号信息
    * */
    @Override
    public boolean createShipNumber(String shipNumber,String admin) {
        ShipNumber shipNumber1 = this.shipNumberMapper.selectByNumber(shipNumber);
        if(shipNumber1 != null){
            return false;
        } else {
            shipNumber1 = new ShipNumber();
            shipNumber1.setShipNumber(shipNumber);
            shipNumber1.setCreator(admin);
            shipNumber1.setUpdater(admin);
            shipNumber1.setDeleteFlag(0);
            String dateNow = new DateUtils().dateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
            Date nowDate = new DateUtils().stringtoDate(dateNow,"yyyy-MM-dd HH:mm:ss");
            shipNumber1.setRegisterDate(dateNow);
            shipNumber1.setUpdateDate(dateNow);
            int status = this.shipNumberMapper.insert(shipNumber1);
            if(status == 1){
                return true;
            } else {
                return false;
            }
        }
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取船号
     * */
    @Override
    public String getShipNumber() {
        List<String> shipNumberList = this.shipNumberMapper.selectByAll();

        JSONArray jsonObject = JSONArray.fromObject(shipNumberList);
        String jsonStr = jsonObject.toString();

        return jsonStr;
    }
}
