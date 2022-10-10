package ku.cs.models.request;

import ku.cs.services.UserDataSource;

public class modelOther {
    private int vote;
    private String detail;

    private UserDataSource request;

    public modelOther(int vote, String detail) {
        this.vote = vote;
        this.detail = detail;
    }

    public void addOther(modelOther other){
        request = new UserDataSource("data/category","other.csv");
        request.writefile_other(other);
    }

    public int getVote() {
        return vote;
    }

    public String getDetail() {
        return detail;
    }
}
