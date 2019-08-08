package cn.teacherbe.controller;

import cn.teacherbe.service.AccSeplenishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("accSeplenishmentController")
@RequestMapping("/seplenishment")
public class AccSeplenishmentController {

    @Autowired
    private AccSeplenishmentService accSeplenishmentService;

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取补料信息
     * */
    @RequestMapping(value = "/getAccSeplenishmentInfo")
    public String getAccSeplenishmentInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate){
        String AccCommonInfo = this.accSeplenishmentService.getAccSeplenishmentInfo(pageNo,pageSize,admin,startDate,endDate);
        return AccCommonInfo;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取未配对已发货信息
     * */
    @RequestMapping(value = "/getLajiInfo")
    public String getLajiInfo(Integer pageNo, Integer pageSize, String admin){
        String AccCommonInfo = this.accSeplenishmentService.getLajiInfo(pageNo,pageSize,admin);
        return AccCommonInfo;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取未配对已发货信息
     * */
    @RequestMapping(value = "/laji")
    public String laji(String idGroup, String admin){
        String AccCommonInfo = this.accSeplenishmentService.laji(idGroup,admin);
        return AccCommonInfo;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 补料确认
     * */
    @RequestMapping(value = "/seplenishmentConfirm")
    public String seplenishmentConfirm(String idGroup,String admin){
        String AccCommonInfo = this.accSeplenishmentService.seplenishmentConfirm(idGroup,admin);
        return AccCommonInfo;
    }

}
