package cn.teacherbe.entity;

public class ShipSegmentation2 {
    private String segmentation;

    private Integer status;

    private Integer number;

    public String getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation == null ? null : segmentation.trim();
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}