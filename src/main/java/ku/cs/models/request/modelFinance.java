package ku.cs.models.request;

import ku.cs.services.UserDataSource;

public class modelFinance {
    private int vote;
    private int amount;
    private String detail;
    private String imagePath;

    private String time;

    private UserDataSource request;

    public modelFinance(int vote, int amount, String detail,  String time) {
        this.vote = vote;
        this.amount = amount;
        this.detail = detail;
    }

    public int getVote() {
        return vote;
    }

    public int getAmount() {
        return amount;
    }

    public String getDetail() {
        return detail;
    }

    public String getImagePath() {
        return imagePath;
    }


    public void addFinance(modelFinance finance){
        request = new UserDataSource("data/category","finance.csv");
        request.writefile_Finance(finance);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
