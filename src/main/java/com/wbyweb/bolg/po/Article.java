package com.wbyweb.bolg.po;

import java.util.Date;
import java.util.List;

public class Article {
    private Short articleId;

    private String articleName;

    private Date articleTime;

    private String articleIp;

    private Integer articleClick;

    private Integer articleType;

    private Integer sortArticleId;

    private Integer userId;

    private Byte typeId;

    private Integer articlePattern;

    private Byte articleUp;

    private Byte articleSupport;

    private String articleImgUrl;

    private String articledescription;

    private String tagcloud;
    private List<String> tagclouds;

    private User user;
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    private ArticleSort articleSort;

    private UserComment userComment;

    public Short getArticleId() {
        return articleId;
    }

    public void setArticleId(Short articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        if(",".equals(articleName.substring(0,1))){
            articleName=articleName.substring(1,articleName.length());
        }
        this.articleName = articleName == null ? null : articleName.trim();
    }

    public Date getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(Date articleTime) {
        this.articleTime = articleTime;
    }

    public String getArticleIp() {
        return articleIp;
    }

    public void setArticleIp(String articleIp) {
        this.articleIp = articleIp == null ? null : articleIp.trim();
    }

    public Integer getArticleClick() {
        return articleClick;
    }

    public void setArticleClick(Integer articleClick) {
        this.articleClick = articleClick;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public Integer getSortArticleId() {
        return sortArticleId;
    }

    public void setSortArticleId(Integer sortArticleId) {
        this.sortArticleId = sortArticleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getTypeId() {
        return typeId;
    }

    public void setTypeId(Byte typeId) {
        this.typeId = typeId;
    }

    public Integer getArticlePattern() {
        return articlePattern;
    }

    public void setArticlePattern(Integer articlePattern) {
        this.articlePattern = articlePattern;
    }

    public Byte getArticleUp() {
        return articleUp;
    }

    public void setArticleUp(Byte articleUp) {
        this.articleUp = articleUp;
    }

    public Byte getArticleSupport() {
        return articleSupport;
    }

    public void setArticleSupport(Byte articleSupport) {
        this.articleSupport = articleSupport;
    }

    public String getArticleImgUrl() {
        return articleImgUrl;
    }

    public void setArticleImgUrl(String articleImgUrl) {
        this.articleImgUrl = articleImgUrl == null ? null : articleImgUrl.trim();
    }

    public String getArticledescription() {
        return articledescription;
    }

    public void setArticledescription(String articledescription) {
        this.articledescription = articledescription == null ? null : articledescription.trim();
    }

    public String getTagcloud() {
        return tagcloud;
    }

    public void setTagcloud(String tagcloud) {
        this.tagcloud = tagcloud == null ? null : tagcloud.trim();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArticleSort getArticleSort() {
        return articleSort;
    }

    public void setArticleSort(ArticleSort articleSort) {
        this.articleSort = articleSort;
    }

    public List<String> getTagclouds() {
        return tagclouds;
    }

    public void setTagclouds(List<String> tagclouds) {
        this.tagclouds = tagclouds;
    }

    public UserComment getUserComment() {
        return userComment;
    }

    public void setUserComment(UserComment userComment) {
        this.userComment = userComment;
    }
}