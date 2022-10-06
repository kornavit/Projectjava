package ku.cs.models;

public class modelBanUser {
    private String username;
    private String detailBan;

    public modelBanUser(String username,String detailBan){
        this.username = username;
        this.detailBan = detailBan;
    }

    public String getUsername() {
        return username;
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
