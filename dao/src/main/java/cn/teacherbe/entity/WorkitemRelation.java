package cn.teacherbe.entity;

import java.util.Date;

public class WorkitemRelation {
    private Integer id;

    private Integer oneCommonId;

    private Integer twoCommonId;

    private String executionCross;

    private String executiveClass;

    private Integer checkIf;

    private String process;

    private Integer workitemStatus;

    private String registerDate;

    private String updateDate;

    private String creator;

    private String updater;

    private Integer deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOneCommonId() {
        return oneCommonId;
    }

    public void setOneCommonId(Integer oneCommonId) {
        this.oneCommonId = oneCommonId;
    }

    public Integer getTwoCommonId() {
        return twoCommonId;
    }

    public void setTwoCommonId(Integer twoCommonId) {
        this.twoCommonId = twoCommonId;
    }

    public String getExecutionCross() {
        return executionCross;
    }

    public void setExecutionCross(String executionCross) {
        this.executionCross = executionCross == null ? null : executionCross.trim();
    }

    public String getExecutiveClass() {
        return executiveClass;
    }

    public void setExecutiveClass(String executiveClass) {
        this.executiveClass = executiveClass == null ? null : executiveClass.trim();
    }

    public Integer getCheckIf() {
        return checkIf;
    }

    public void setCheckIf(Integer checkIf) {
        this.checkIf = checkIf;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process == null ? null : process.trim();
    }

    public Integer getWorkitemStatus() {
        return workitemStatus;
    }

    public void setWorkitemStatus(Integer workitemStatus) {
        this.workitemStatus = workitemStatus;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
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