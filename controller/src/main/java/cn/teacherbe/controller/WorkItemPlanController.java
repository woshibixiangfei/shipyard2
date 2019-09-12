package cn.teacherbe.controller;

import cn.teacherbe.service.ShipNumberService;
import cn.teacherbe.service.WorkItemRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author 毕翔斐
 * @version 1.0
 * @description
 * */
@RestController("workItemPlanController")
@RequestMapping("/workItem")
public class WorkItemPlanController {

    @Autowired
    private WorkItemRelationService workItemRelationService;

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取下计划列表
     * */
    @RequestMapping(value = "/getPlan")
    public String getPlan(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate){
        String plan = this.workItemRelationService.getPlan(pageNo,pageSize,admin,startDate,endDate);
        return plan;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 下计划
     * */
    @RequestMapping(value = "/underPlan")
    public String underPlan(String idGroup, String admin){
        String underPlan = this.workItemRelationService.underPlan(idGroup,admin);
        return underPlan;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取任务列表
     * */
    @RequestMapping(value = "/getTaskInfo")
    public String getTaskInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate){
        String TaskInfo = this.workItemRelationService.getTaskInfo(pageNo,pageSize,admin,startDate,endDate);
        return TaskInfo;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 认领
     * */
    @RequestMapping(value = "/claim")
    public String claim(String admin,String idGroup){
        String claim = this.workItemRelationService.claim(admin,idGroup);
        return claim;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 装配列表
     * */
    @RequestMapping(value = "/getAssemblyInfo")
    public String getAssemblyInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate){
        String claim = this.workItemRelationService.getAssemblyInfo(pageNo,pageSize,admin,startDate,endDate);
        return claim;
    }
    /*
     * @author 毕翔斐
     * @version 1.0
     * @description
     * */
    @RequestMapping(value = "/getFuckEveryDay")
    public String getFuckEveryDay(Integer id){
        String claim = this.workItemRelationService.getFuckEveryDay(id);
        return claim;
    }
    /*
     * @author 毕翔斐
     * @version 1.0
     * @description
     * */
    @RequestMapping(value = "/getFuckEveryDay2")
    public String getFuckEveryDay2(Integer id,String admin){
        String claim = this.workItemRelationService.getFuckEveryDay2(id,admin);
        return claim;
    }
    /*
     * @author 毕翔斐
     * @version 1.0
     * @description
     * */
    @RequestMapping(value = "/getFuckEveryDay3")
    public String getFuckEveryDay3(String fuckGroup,String admin){
        String claim = this.workItemRelationService.getFuckEveryDay3(fuckGroup,admin);
        return claim;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 装配
     * */
    @RequestMapping(value = "/assembly")
    public String assembly(String idGroup,String admin){
        String assembly = this.workItemRelationService.assembly(idGroup,admin);
        return assembly;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取焊接列表
     * */
    @RequestMapping(value = "/getWeldingInfo")
    public String getWeldingInfo(Integer pageNo, Integer pageSize, String admin,Integer type,String startDate,String endDate){
        String getWeldingInfo = this.workItemRelationService.getWeldingInfo(pageNo,pageSize,admin,type,startDate,endDate);
        return getWeldingInfo;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 焊接
     * */
    @RequestMapping(value = "/welding")
    public String welding(String idGroup,String admin,Integer type){
        String welding = this.workItemRelationService.welding(idGroup,admin,type);
        return welding;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 焊接统计
     * */
    @RequestMapping(value = "/getWeldingSelect")
    public String getWeldingSelect(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate){
        String welding = this.workItemRelationService.getWeldingSelect(pageNo,pageSize,admin,startDate,endDate);
        return welding;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取出库列表
     * */
    @RequestMapping(value = "/getOutInfo")
    public String getOutInfo(Integer pageNo, Integer pageSize, String admin,String adminRole,String shipNumber,String segmentation,String startDate,String endDate){
        String getOutInfo = this.workItemRelationService.getOutInfo(pageNo,pageSize,admin,shipNumber,segmentation,startDate,endDate);
        return getOutInfo;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取出库记录
     * */
    @RequestMapping(value = "/getOutInfo2")
    public String getOutInfo2(Integer pageNo, Integer pageSize, String admin){
        String getOutInfo = this.workItemRelationService.getOutInfo2(pageNo,pageSize,admin);
        return getOutInfo;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 出库
     * */
    @RequestMapping(value = "/out")
    public String out(String idGroup,String admin,String carNumber){
        String welding = this.workItemRelationService.out(idGroup,admin,carNumber);
        return welding;
    }
}
