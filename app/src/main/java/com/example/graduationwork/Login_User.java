package com.example.graduationwork;

public class Login_User {
    private int seq;
    private String email;
    private String pw;
    private String name;
    private String number;
    private String weight;
    private String gender;
    private String foot;
    private String tall;
    private int post;
    private int follower;

    public int getPost() {
        return post;
    }

    public String getWeight() {
        return weight;
    }

    public String getFoot() {
        return foot;
    }

    public String getTall() {
        return tall;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    private int following;
    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }

    public void setTall(String tall) {
        this.tall = tall;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
