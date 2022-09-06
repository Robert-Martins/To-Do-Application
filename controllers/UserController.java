package controllers;

import java.io.*;

import models.User;

public class UserController {

    File fil = new File("arquivo.txt");

    public UserController(){}

    public User initializeUser(String name){
        User user = new User(name);
        saveUser(user);
        return user;
    }

    public User getUser(){
        User user = new User();
        try{

            ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("arquivo.txt")));
            User savedUser = (User)objectIn.readObject();
            objectIn.close();

            user = savedUser;

        }catch (EOFException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void saveUser(User user){

        emptySavedData();
        try{
            ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("arquivo.txt")));
            objectOut.writeObject(user);
            objectOut.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void emptySavedData(){
        try{
            FileReader fr = new FileReader(fil);
            BufferedReader br = new BufferedReader(fr);

            br.close();
            fr.close();
            FileWriter fw2 = new FileWriter(fil, true);
            fw2.close();

            FileWriter fw = new FileWriter(fil);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.close();
            fw.close();

        }catch(IOException ex){
        }
    }

}