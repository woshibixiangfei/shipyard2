package cn.teacherbe.service;

public interface ShipSegmentationService {

    public boolean createShipSegmentation(String shipNumber,String admin,Integer size);

    public String getShipSegmentation(String shipNumber,Integer pageNo,Integer pageSize);
}
