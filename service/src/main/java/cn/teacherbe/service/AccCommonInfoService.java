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
                            Integer pageNo,Integer pageSize,String admin,String startDate,String endDate);

    String generateInvoice(String idGroup, String admin);

    public String getAccInecoming(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate);

    public String getInvoiceInfo(Integer pageNo, Integer pageSize, String admin,String startDate,String endDate);

    public String ship(String idGroup, String admin);
}
