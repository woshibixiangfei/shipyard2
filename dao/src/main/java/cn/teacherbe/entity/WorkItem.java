package cn.teacherbe.entity;

import java.util.Date;

public class WorkItem {
    private Integer id;

    private Integer workitemId;

    private Integer straight;

    private Integer linear;

    private Integer soldering;

    private Integer linearWelding;

    private Date registerDate;

    private Date updateDate;

    private String creator;

    private String updater;

    private Integer deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkitemId() {
        return workitemId;
    }

    public void setWorkitemId(Integer workitemId) {
        this.workitemId = workitemId;
    }

    public Integer getStraight() {
        return straight;
    }

    public void setStraight(Integer straight) {
        this.straight = straight;
    }

    public Integer getLinear() {
        return linear;
    }

    public void setLinear(Integer linear) {
        this.linear = linear;
    }

    public Integer getSoldering() {
        return soldering;
    }

    public void setSoldering(Integer soldering) {
        this.soldering = soldering;
    }

    public Integer getLinearWelding() {
        return linearWelding;
    }

    public void setLinearWelding(Integer linearWelding) {
        this.linearWelding = linearWelding;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}