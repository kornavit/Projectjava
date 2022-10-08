package ku.cs.models;

public class modelBanUser {
    private String username;
    private String detailBan;
    private int countLogin;

    public modelBanUser(String username,int countLogin,String detailBan){
        this.username = username;
        this.detailBan = detailBan;
        this.countLogin = countLogin;
    }

    public String getUsername() {
        return username;
    }

    public int getCountLogin() {
        return countLogin;
    }

    public void setCountLogin(int countLogin) {
        this.countLogin = countLogin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDetailBan() {
        return detailBan;
    }

    public void setDetailBan(String detailBan) {
        this.detailBan = detailBan;
    }

    @Override
    public String toString() {
        return username;
    }
}
