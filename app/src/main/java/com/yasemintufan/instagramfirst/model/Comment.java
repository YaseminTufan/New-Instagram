package com.yasemintufan.instagramfirst.model;

public class Comment {
    private String id;
    private String comment;
    private String publiser;

   public Comment() {

   }

    public Comment(String id, String comment, String publiser) {
        this.id = id;
        this.comment = comment;
        this.publiser = publiser;
    }

    public String getComment() {
        return comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPubliser() {
        return publiser;
    }

    public void setPubliser(String publiser) {
        this.publiser = publiser;
    }
}
