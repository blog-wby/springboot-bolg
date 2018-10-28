package com.wbyweb.bolg.po;

public class SystemMessage {
    private Integer systemId;

    private Integer sendId;

    private Byte groupId;

    private Integer sendDefault;

    private String systemTopic;

    private String systemContent;

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Byte getGroupId() {
        return groupId;
    }

    public void setGroupId(Byte groupId) {
        this.groupId = groupId;
    }

    public Integer getSendDefault() {
        return sendDefault;
    }

    public void setSendDefault(Integer sendDefault) {
        this.sendDefault = sendDefault;
    }

    public String getSystemTopic() {
        return systemTopic;
    }

    public void setSystemTopic(String systemTopic) {
        this.systemTopic = systemTopic == null ? null : systemTopic.trim();
    }

    public String getSystemContent() {
        return systemContent;
    }

    public void setSystemContent(String systemContent) {
        this.systemContent = systemContent == null ? null : systemContent.trim();
    }
}