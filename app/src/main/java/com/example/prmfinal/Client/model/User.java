package com.example.prmfinal.Client.model;
public class User {
    //Begin login
        private String id;
        private String userName;
        private String password;
        private String role;
    //End login

    //Begin contact
        private String email;
        private String phoneNumber;
        private String name;
    //End contact

    //Begin training infor
        private float height;
        private float weight;
        private int maxRepPushUp;
        private int maxRepPullUp;
        private int maxRepSquats;
        private String mucDichTap;
        private String level;
    //End training infor

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getMaxRepPushUp() {
        return maxRepPushUp;
    }

    public void setMaxRepPushUp(int maxRepPushUp) {
        this.maxRepPushUp = maxRepPushUp;
    }

    public int getMaxRepPullUp() {
        return maxRepPullUp;
    }

    public void setMaxRepPullUp(int maxRepPullUp) {
        this.maxRepPullUp = maxRepPullUp;
    }

    public int getMaxRepSquats() {
        return maxRepSquats;
    }

    public void setMaxRepSquats(int maxRepSquats) {
        this.maxRepSquats = maxRepSquats;
    }

    public String getMucDichTap() {
        return mucDichTap;
    }

    public void setMucDichTap(String mucDichTap) {
        this.mucDichTap = mucDichTap;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
