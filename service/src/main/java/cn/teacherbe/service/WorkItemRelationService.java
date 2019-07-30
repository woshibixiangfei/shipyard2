package cn.teacherbe.service;

public interface WorkItemRelationService {

    public String getPlan(Integer pageNo, Integer pageSize, String admin);

    public String underPlan(String idGroup, String kua, String admin);

    public String getTaskInfo(Integer pageNo, Integer pageSize, String admin);

    public String claim(String admin,String idGroup);

    public String getAssemblyInfo(Integer pageNo, Integer pageSize, String admin);

    public String assembly(String idGroup,String admin);

    public String getWeldingInfo(Integer pageNo, Integer pageSize, String admin,Integer type);

    public String welding(String idGroup,String admin,Integer type);

    public String getOutInfo(Integer pageNo, Integer pageSize, String admin);
}
