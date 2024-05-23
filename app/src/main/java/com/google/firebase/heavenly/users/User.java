package com.google.firebase.heavenly.users;

public class User {
    public String uid, username, Imageprofile,imageLifeStyle, imagefood, other, imagelandscape, architecture, animals;

    public User(String uid,String username, String imageprofile, String imageLifeStyle, String imagefood,
                String other, String imagelandscape, String architecture, String animals){
        this.uid = uid;
        this.animals=animals;
        this.architecture=architecture;
        this.imagelandscape=imagelandscape;
        this.other = other;
        this.imagefood = imagefood;
        this.imageLifeStyle = imageLifeStyle;
        this.Imageprofile = imageprofile;
        this.username = username;
    }
}
