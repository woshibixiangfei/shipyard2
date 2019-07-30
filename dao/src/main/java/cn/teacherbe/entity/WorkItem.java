package cn.teacherbe.entity;

import java.util.Date;

public class WorkItem {
    private Integer id;

    private Integer workitemId;

    private String now;

    private Integer type;

    private String description;

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

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now == null ? null : now.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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