package cn.teacherbe.entity;

public class AccSeplenishmentInfo {
    private Integer id;

    private String shipNumber;

    private String segmentation;

    private Integer batch;

    private String partNumber;

    private Integer replenishmentStatus;

    private Integer arrivalStatus;

    private String numberPlate;

    private String group;

    private String updateDate;

    private String updater;

    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShipNumber() {
        return shipNumber;
    }

    public void setShipNumber(String shipNumber) {
        this.shipNumber = shipNumber;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public Integer getReplenishmentStatus() {
        return replenishmentStatus;
    }

    public void setReplenishmentStatus(Integer replenishmentStatus) {
        this.replenishmentStatus = replenishmentStatus;
    }

    public Integer getArrivalStatus() {
        return arrivalStatus;
    }

    public void setArrivalStatus(Integer arrivalStatus) {
        this.arrivalStatus = arrivalStatus;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}