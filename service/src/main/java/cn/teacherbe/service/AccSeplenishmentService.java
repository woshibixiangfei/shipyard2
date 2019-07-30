package cn.teacherbe.service;

public interface AccSeplenishmentService {

    public String getAccSeplenishmentInfo(Integer pageNo, Integer pageSize, String admin);

    public String seplenishmentConfirm(String idGroup,String admin);
}
