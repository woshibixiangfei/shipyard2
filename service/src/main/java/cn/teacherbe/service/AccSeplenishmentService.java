package cn.teacherbe.service;

public interface AccSeplenishmentService {

    public String getAccSeplenishmentInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate);

    public String seplenishmentConfirm(String idGroup,String admin);
}
