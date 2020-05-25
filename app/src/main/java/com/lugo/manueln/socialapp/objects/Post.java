package com.lugo.manueln.socialapp.objects;

public class Post {

    int id;
    String userName,title,body,image;

    public Post(String userName, int id, String title, String body,String image) {
        this.userName=userName;
        this.id = id;
        this.title = title;
        this.body = body;
        this.image= image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
