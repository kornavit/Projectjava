package ku.cs.models;

import java.util.ArrayList;

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
}