package controllers;

import models.User;

public class UserController {

    public UserController(){}

    public void initializeUser(String name){
        var user = new User();
        saveUser(user);
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
