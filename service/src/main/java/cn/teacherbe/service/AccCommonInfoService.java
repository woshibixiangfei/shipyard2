package cn.teacherbe.service;

import cn.teacherbe.entity.AccCommonInfo;

public interface AccCommonInfoService {
    int deleteByPrimaryKey(Integer id);

    int insert(AccCommonInfo record);

    int insertSelective(AccCommonInfo record);

    AccCommonInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccCommonInfo record);

    int updateByPrimaryKey(AccCommonInfo record);

    String getAccCommonInfo(String shipNumber,String segmentation,
                            Integer pageNo,Integer pageSize,String adminRole);

    String generateInvoice(String idGroup, String admin, String adminRole);

    public String getAccInecoming(Integer pageNo, Integer pageSize, String adminRole);

    public String getInvoiceInfo(Integer pageNo, Integer pageSize, String adminRole);

    public String ship(String idGroup, String admin, String adminRole);
}
