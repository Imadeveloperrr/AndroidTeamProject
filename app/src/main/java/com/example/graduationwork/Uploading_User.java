package com.example.graduationwork;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Uploading_User implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("user_email")
    private String user_email;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("postlike")
    private int postlike;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPostlike() {
        return postlike;
    }

    public void setPostlike(int postlike) {
        this.postlike = postlike;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @SerializedName("success")
    private boolean success;

}
