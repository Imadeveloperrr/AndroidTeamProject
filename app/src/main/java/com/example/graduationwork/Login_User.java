package com.example.graduationwork;

public class Login_User {
    private int seq;
    private String email;
    private String pw;
    private String name;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    private String number;
    private String weight;

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }

    public void setTall(String tall) {
        this.tall = tall;
    }

    private String gender;
    private String foot;
    private String tall;

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
