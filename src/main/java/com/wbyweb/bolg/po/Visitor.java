package com.wbyweb.bolg.po;

public class Visitor {
    private Integer vId;

    private Integer visitorId;

    private Integer visitorTime;

    private Integer userId;

    private String visitorIp;

    private Integer typeId;

    private Integer whereId;

    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public Integer getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }

    public Integer getVisitorTime() {
        return visitorTime;
    }

    public void setVisitorTime(Integer visitorTime) {
        this.visitorTime = visitorTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getVisitorIp() {
        return visitorIp;
    }

    public void setVisitorIp(String visitorIp) {
        this.visitorIp = visitorIp == null ? null : visitorIp.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getWhereId() {
        return whereId;
    }

    public void setWhereId(Integer whereId) {
        this.whereId = whereId;
    }
}