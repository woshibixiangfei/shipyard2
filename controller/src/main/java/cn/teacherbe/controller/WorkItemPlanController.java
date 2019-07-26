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
    public String getPlan(Integer pageNo, Integer pageSize, String adminRole){
        String plan = this.workItemRelationService.getPlan(pageNo,pageSize,adminRole);
        return plan;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 下计划
     * */
    @RequestMapping(value = "/underPlan")
    public String underPlan(String idGroup, String kua, String admin, String adminRole){
        String underPlan = this.workItemRelationService.underPlan(idGroup,kua,admin,adminRole);
        return underPlan;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取任务列表
     * */
    @RequestMapping(value = "/getTaskInfo")
    public String getTaskInfo(Integer pageNo, Integer pageSize, String admin,String adminRole){
        String TaskInfo = this.workItemRelationService.getTaskInfo(pageNo,pageSize,admin,adminRole);
        return TaskInfo;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 认领
     * */
    @RequestMapping(value = "/claim")
    public String claim(String admin,String adminRole,String idGroup){
        String claim = this.workItemRelationService.claim(admin,adminRole,idGroup);
        return claim;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 认领
     * */
    @RequestMapping(value = "/getAssemblyInfo")
    public String getAssemblyInfo(Integer pageNo, Integer pageSize, String admin,String adminRole){
        String claim = this.workItemRelationService.getAssemblyInfo(pageNo,pageSize,admin,adminRole);
        return claim;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 装配
     * */
    @RequestMapping(value = "/assembly")
    public String assembly(String idGroup,String admin,String adminRole){
        String assembly = this.workItemRelationService.assembly(idGroup,admin,adminRole);
        return assembly;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 获取焊接列表
     * */
    @RequestMapping(value = "/getWeldingInfo")
    public String getWeldingInfo(Integer pageNo, Integer pageSize, String admin,Integer type,String adminRole){
        String getWeldingInfo = this.workItemRelationService.getWeldingInfo(pageNo,pageSize,admin,type,adminRole);
        return getWeldingInfo;
    }

    /*
     * @author 毕翔斐
     * @version 1.0
     * @description 焊接
     * */
    @RequestMapping(value = "/welding")
    public String welding(String idGroup,String admin,Integer type,String adminRole){
        String welding = this.workItemRelationService.welding(idGroup,admin,type,adminRole);
        return welding;
    }
}