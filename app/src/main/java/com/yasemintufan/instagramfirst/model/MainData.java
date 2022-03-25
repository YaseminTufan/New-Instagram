package com.yasemintufan.instagramfirst.model;

public class MainData {
    Integer imageUrl;
    User user;

    public MainData(Integer imageUrl, User user) {
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
