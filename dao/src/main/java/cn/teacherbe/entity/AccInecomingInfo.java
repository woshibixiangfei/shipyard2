package cn.teacherbe.entity;

public class AccInecomingInfo {

    private String shipNumber;

    private Integer batch;

    private String segmentation;

    private String partNumber;

    private Integer numberParts;

    private Integer replenishmentStatus;

    private String registerDate;

    private String numberPlate;

    private String group;

    private String updater;

    private String text;

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

    public String getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public Integer getNumberParts() {
        return numberParts;
    }

    public void setNumberParts(Integer numberParts) {
        this.numberParts = numberParts;
    }

    public Integer getReplenishmentStatus() {
        return replenishmentStatus;
    }

    public void setReplenishmentStatus(Integer replenishmentStatus) {
        this.replenishmentStatus = replenishmentStatus;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
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