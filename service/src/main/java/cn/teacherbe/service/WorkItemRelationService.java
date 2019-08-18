package cn.teacherbe.service;

public interface WorkItemRelationService {

    public String getPlan(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate);

    public String underPlan(String idGroup, String admin);

    public String getTaskInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate);

    public String claim(String admin,String idGroup);

    public String getAssemblyInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate);

    public String assembly(String idGroup,String admin);

    public String getWeldingInfo(Integer pageNo, Integer pageSize, String admin,Integer type,String startDate,String endDate);

    public String welding(String idGroup,String admin,Integer type);

    public String getOutInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate);

    public String getWeldingSelect(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate);

    public String out(String idGroup,String admin,String carNumber);
}
