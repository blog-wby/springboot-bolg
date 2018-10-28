package com.wbyweb.bolg.po;

public class PhotoSort {
    private Integer sortImgId;

    private String sortImgName;

    private String sortImgType;

    private String imgPassword;

    private Integer userId;

    private String imgSortQuestion;

    private String imgSortAnswer;

    private Integer typeId;

    private Integer topPicSrc;

    public Integer getSortImgId() {
        return sortImgId;
    }

    public void setSortImgId(Integer sortImgId) {
        this.sortImgId = sortImgId;
    }

    public String getSortImgName() {
        return sortImgName;
    }

    public void setSortImgName(String sortImgName) {
        this.sortImgName = sortImgName == null ? null : sortImgName.trim();
    }

    public String getSortImgType() {
        return sortImgType;
    }

    public void setSortImgType(String sortImgType) {
        this.sortImgType = sortImgType == null ? null : sortImgType.trim();
    }

    public String getImgPassword() {
        return imgPassword;
    }

    public void setImgPassword(String imgPassword) {
        this.imgPassword = imgPassword == null ? null : imgPassword.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImgSortQuestion() {
        return imgSortQuestion;
    }

    public void setImgSortQuestion(String imgSortQuestion) {
        this.imgSortQuestion = imgSortQuestion == null ? null : imgSortQuestion.trim();
    }

    public String getImgSortAnswer() {
        return imgSortAnswer;
    }

    public void setImgSortAnswer(String imgSortAnswer) {
        this.imgSortAnswer = imgSortAnswer == null ? null : imgSortAnswer.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTopPicSrc() {
        return topPicSrc;
    }

    public void setTopPicSrc(Integer topPicSrc) {
        this.topPicSrc = topPicSrc;
    }
}