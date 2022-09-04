package ku.cs.models;

import java.util.ArrayList;

public class modelRegisterList {
    private ArrayList<modelregister> users;

    public modelRegisterList() {
        users = new ArrayList<>();
    }
    public void addUser(modelregister user) {

        users.add(user);
    }
    public ArrayList<modelregister> getAllUsers() {
        return users;
    }
}