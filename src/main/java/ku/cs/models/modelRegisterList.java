package ku.cs.models;

import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.LinkedList;

public class modelRegisterList {
    private ArrayList<modelRegister> users;

    public modelRegisterList() {
        users = new ArrayList<>();
    }
    public void addUser(modelRegister user) {
        users.add(user);
    }
    public ArrayList<modelRegister> getAllUsers() {
        return users;
    }
    public int length(){return users.size();}
    public modelRegister getuser(int i){return users.get(i);}
}