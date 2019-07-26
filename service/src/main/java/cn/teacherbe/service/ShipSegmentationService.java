package cn.teacherbe.service;

public interface ShipSegmentationService {

    public boolean createShipSegmentation(String shipNumber,String admin);

    public String getShipSegmentation(String shipNumber,Integer pageNo,Integer pageSize);
}
