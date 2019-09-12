package cn.teacherbe.entity;

public class WorkitemRelationPlan {
    private Integer id;

    private String shipNumber;

    //private String shipNumber2;

    private Integer batch;

   // private Integer batch2;

    private String segmentation;

   // private String segmentation2;

    private String partNumber;

    private String updateDate;

    private Integer length;

    private Integer workitemStatus;

    private String process;

    //private String partNumber2;

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

    /*public String getShipNumber2() {
        return shipNumber2;
    }

    public void setShipNumber2(String shipNumber2) {
        this.shipNumber2 = shipNumber2;
    }*/

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    /*public Integer getBatch2() {
        return batch2;
    }

    public void setBatch2(Integer batch2) {
        this.batch2 = batch2;
    }*/

    public String getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    /*public String getSegmentation2() {
        return segmentation2;
    }

    public void setSegmentation2(String segmentation2) {
        this.segmentation2 = segmentation2;
    }*/

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    /*public String getPartNumber2() {
        return partNumber2;
    }

    public void setPartNumber2(String partNumber2) {
        this.partNumber2 = partNumber2;
    }*/

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWorkitemStatus() {
        return workitemStatus;
    }

    public void setWorkitemStatus(Integer workitemStatus) {
        this.workitemStatus = workitemStatus;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}