package com.yasemintufan.instagramfirst.model;

public class Post {
    private String description;
    private String imageurl;
    private String postid;
    private String publiser;

    public Post() {
    }

    public Post(String description, String imageurl, String postid, String publiser) {
        this.description = description;
        this.imageurl = imageurl;
        this.postid = postid;
        this.publiser = publiser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPubliser() {
        return publiser;
    }

    public void setPubliser(String publiser) {
        this.publiser = publiser;
    }
}
