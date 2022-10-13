package ku.cs.models;

import java.util.ArrayList;

public class modelBanUserList {
    private ArrayList<modelBanUser> banUsers;

    public modelBanUserList() {
        banUsers = new ArrayList<>();
    }
    public void addBanUsers(modelBanUser user) {
        banUsers.add(user);
    }
    public ArrayList<modelBanUser> getAllUsers() {
        return banUsers;
    }
}
