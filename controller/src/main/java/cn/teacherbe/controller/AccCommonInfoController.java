package cn.teacherbe.controller;

import cn.teacherbe.service.AccCommonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("accCommonInfoController")
@RequestMapping("/commonInfo")
public class AccCommonInfoController {

    @Autowired
    private AccCommonInfoService accCommonInfoService;

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取物料信息
     * */
    @RequestMapping(value = "/getAccCommonInfo")
    public String getAccCommonInfo(String shipNumber, String segmentation, Integer pageNo, Integer pageSize,String type, String admin,String startDate,String endDate){
        String AccCommonInfo = this.accCommonInfoService.getAccCommonInfo(shipNumber,segmentation,pageNo,pageSize,type,admin,startDate,endDate);
        return AccCommonInfo;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 生成发货单
     * */
    @RequestMapping(value = "/generateInvoice")
    public String generateInvoice(String idGroup, String admin){
        String status = this.accCommonInfoService.generateInvoice(idGroup,admin);
        return status;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取发货单信息
     * */
    @RequestMapping(value = "/getInvoiceInfo")
    public String getInvoiceInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate){
        String InvoiceInfo = this.accCommonInfoService.getInvoiceInfo(pageNo,pageSize,admin,startDate,endDate);
        return InvoiceInfo;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 发货
     * */
    @RequestMapping(value = "/ship")
    public String ship(String idGroup, String admin){
        String status = this.accCommonInfoService.ship(idGroup,admin);
        return status;
    }
}
