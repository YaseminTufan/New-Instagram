package com.yasemintufan.instagramfirst.model;

public class User {
    String userName;
    String userComment;
    Integer userCircle;
    String userLocation;

    public User(String userName, String userComment, Integer userCircle, String userLocation) {
        this.userName = userName;
        this.userComment = userComment;
        this.userCircle = userCircle;
        this.userLocation = userLocation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Integer getUserCircle() {
        return userCircle;
    }

    public void setUserCircle(Integer userCircle) {
        this.userCircle = userCircle;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }
}
