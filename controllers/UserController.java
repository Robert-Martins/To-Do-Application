package controllers;

import models.User;

public class UserController {

    public UserController(){}

    public User initializeUser(String name){
        var user = new User(name);
        saveUser(user);
        return user;
    }

    public User getUser(){
        return new User();
    }

    public void saveUser(User user){
        emptySavedData();
    }

    public void emptySavedData(){

    }

}
