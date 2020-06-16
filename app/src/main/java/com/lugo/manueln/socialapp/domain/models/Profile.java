package com.lugo.manueln.socialapp.domain.models;

public class Profile {

    String userName,name,imageProfile;
    int numberPosts;

    public Profile(String userName, String name,int numberPosts,String imageProfile) {
        this.userName = userName;
        this.name = name;
        this.imageProfile = imageProfile;
        this.numberPosts = numberPosts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public int getNumberPosts() {
        return numberPosts;
    }

    public void setNumberPost(int numberPosts) {
        this.numberPosts = numberPosts;
    }
}