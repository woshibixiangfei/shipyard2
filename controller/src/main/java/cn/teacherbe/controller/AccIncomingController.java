package cn.teacherbe.controller;

import cn.teacherbe.service.AccCommonInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("accIncomingController")
@RequestMapping("/incoming")
public class AccIncomingController {

    @Autowired
    private AccCommonInfoService accCommonInfoService;

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取来料信息
     * */
    @RequestMapping(value = "/getAccInecomingInfo")
    public String getAccInecomingInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate){
        String AccCommonInfo = this.accCommonInfoService.getAccInecoming(pageNo,pageSize,admin,startDate,endDate);
        return AccCommonInfo;
    }

}
