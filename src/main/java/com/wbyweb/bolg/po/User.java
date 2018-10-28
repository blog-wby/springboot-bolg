package com.wbyweb.bolg.po;

public class User {
    private Integer userId;

    private Integer groupId;

    private String userName;

    private String userPwd;

    private Integer userPhone;

    private String userSex;

    private Integer userQq;

    private String userEmail;

    private String userAddress;

    private Integer userMark;

    private Byte userRankId;

    private String userLastLoginIp;

    private Integer userBirthday;

    private String userDescription;

    private String userImageUrl;

    private String userSchool;

    private Integer userRegisterTime;

    private String userRegisterIp;

    private Integer userLastUpdateTime;

    private String userWeibo;

    private String userBloodType;

    private String userSays;

    private Byte userLock;

    private Byte userFreeze;

    private String userPower;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userPhone=" + userPhone +
                ", userSex='" + userSex + '\'' +
                ", userQq=" + userQq +
                ", userEmail='" + userEmail + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userMark=" + userMark +
                ", userRankId=" + userRankId +
                ", userLastLoginIp='" + userLastLoginIp + '\'' +
                ", userBirthday=" + userBirthday +
                ", userDescription='" + userDescription + '\'' +
                ", userImageUrl='" + userImageUrl + '\'' +
                ", userSchool='" + userSchool + '\'' +
                ", userRegisterTime=" + userRegisterTime +
                ", userRegisterIp='" + userRegisterIp + '\'' +
                ", userLastUpdateTime=" + userLastUpdateTime +
                ", userWeibo='" + userWeibo + '\'' +
                ", userBloodType='" + userBloodType + '\'' +
                ", userSays='" + userSays + '\'' +
                ", userLock=" + userLock +
                ", userFreeze=" + userFreeze +
                ", userPower='" + userPower + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public Integer getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Integer userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public Integer getUserQq() {
        return userQq;
    }

    public void setUserQq(Integer userQq) {
        this.userQq = userQq;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public Integer getUserMark() {
        return userMark;
    }

    public void setUserMark(Integer userMark) {
        this.userMark = userMark;
    }

    public Byte getUserRankId() {
        return userRankId;
    }

    public void setUserRankId(Byte userRankId) {
        this.userRankId = userRankId;
    }

    public String getUserLastLoginIp() {
        return userLastLoginIp;
    }

    public void setUserLastLoginIp(String userLastLoginIp) {
        this.userLastLoginIp = userLastLoginIp == null ? null : userLastLoginIp.trim();
    }

    public Integer getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Integer userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription == null ? null : userDescription.trim();
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl == null ? null : userImageUrl.trim();
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool == null ? null : userSchool.trim();
    }

    public Integer getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(Integer userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public String getUserRegisterIp() {
        return userRegisterIp;
    }

    public void setUserRegisterIp(String userRegisterIp) {
        this.userRegisterIp = userRegisterIp == null ? null : userRegisterIp.trim();
    }

    public Integer getUserLastUpdateTime() {
        return userLastUpdateTime;
    }

    public void setUserLastUpdateTime(Integer userLastUpdateTime) {
        this.userLastUpdateTime = userLastUpdateTime;
    }

    public String getUserWeibo() {
        return userWeibo;
    }

    public void setUserWeibo(String userWeibo) {
        this.userWeibo = userWeibo == null ? null : userWeibo.trim();
    }

    public String getUserBloodType() {
        return userBloodType;
    }

    public void setUserBloodType(String userBloodType) {
        this.userBloodType = userBloodType == null ? null : userBloodType.trim();
    }

    public String getUserSays() {
        return userSays;
    }

    public void setUserSays(String userSays) {
        this.userSays = userSays == null ? null : userSays.trim();
    }

    public Byte getUserLock() {
        return userLock;
    }

    public void setUserLock(Byte userLock) {
        this.userLock = userLock;
    }

    public Byte getUserFreeze() {
        return userFreeze;
    }

    public void setUserFreeze(Byte userFreeze) {
        this.userFreeze = userFreeze;
    }

    public String getUserPower() {
        return userPower;
    }

    public void setUserPower(String userPower) {
        this.userPower = userPower == null ? null : userPower.trim();
    }
}