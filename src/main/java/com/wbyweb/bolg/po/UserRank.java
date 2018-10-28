package com.wbyweb.bolg.po;

public class UserRank {
    private Integer rankId;

    private Short userRankId;

    private Integer rankMark;

    private String rankName;

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public Short getUserRankId() {
        return userRankId;
    }

    public void setUserRankId(Short userRankId) {
        this.userRankId = userRankId;
    }

    public Integer getRankMark() {
        return rankMark;
    }

    public void setRankMark(Integer rankMark) {
        this.rankMark = rankMark;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName == null ? null : rankName.trim();
    }
}