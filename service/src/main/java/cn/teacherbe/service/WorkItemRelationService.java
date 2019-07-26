package cn.teacherbe.service;

public interface WorkItemRelationService {

    public String getPlan(Integer pageNo, Integer pageSize, String adminRole);

    public String underPlan(String idGroup, String kua, String admin, String adminRole);

    public String getTaskInfo(Integer pageNo, Integer pageSize, String admin,String adminRole);

    public String claim(String admin,String adminRole,String idGroup);

    public String getAssemblyInfo(Integer pageNo, Integer pageSize, String admin,String adminRole);

    public String assembly(String idGroup,String admin,String adminRole);

    public String getWeldingInfo(Integer pageNo, Integer pageSize, String admin,Integer type,String adminRole);

    public String welding(String idGroup,String admin,Integer type,String adminRole);
}
