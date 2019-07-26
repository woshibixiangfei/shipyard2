package cn.teacherbe.service;

public interface AccSeplenishmentService {

    public String getAccSeplenishmentInfo(Integer pageNo, Integer pageSize, String adminRole);

    public String seplenishmentConfirm(String idGroup,String admin,String adminRole);
}
