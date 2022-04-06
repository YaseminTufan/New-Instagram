package com.yasemintufan.instagramfirst.model;

public class Comment {
    private String comment;
    private String publiser;

   public Comment() {

   }

    public Comment(String comment, String publiser) {
        this.comment = comment;
        this.publiser = publiser;
    }

    public String getComment() {
        return comment;
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
