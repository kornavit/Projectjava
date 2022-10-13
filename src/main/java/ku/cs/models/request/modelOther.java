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

    public int getVote() {
        return vote;
    }

    public String getDetail() {
        return detail;
    }
}
