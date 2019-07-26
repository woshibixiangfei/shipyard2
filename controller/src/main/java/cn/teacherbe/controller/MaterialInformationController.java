package cn.teacherbe.controller;

import cn.teacherbe.service.ShipNumberService;
import cn.teacherbe.service.ShipSegmentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author 毕翔斐
 * @version 1.0
 * @description
 * */
@RestController("materialInformationController")
@RequestMapping("/material")
public class MaterialInformationController {

    @Autowired
    private ShipNumberService shipNumberService;
    @Autowired
    private ShipSegmentationService shipSegmentationService;

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取船号
     * */
    @RequestMapping(value = "/getShipNumber")
    public String getShipNumber(){
        String shipNumber = this.shipNumberService.getShipNumber();
        return shipNumber;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取分段
     * */
    @RequestMapping(value = "/getShipSegmentation")
    public String getShipSegmentation(String shipNumber, Integer pageNo, Integer pageSize){
        String shipSegmentation = this.shipSegmentationService.getShipSegmentation(shipNumber,pageNo,pageSize);
        return shipSegmentation;
    }
}

