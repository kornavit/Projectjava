package ku.cs.models.request;

import ku.cs.services.UserDataSource;

public class modelTraffic {
    private int vote;
    private String location;
    private String detail;
    private String imagePath;

    private UserDataSource request;

    public modelTraffic(Integer vote, String location, String detail) {
        this.vote = vote;
        this.location = location;
        this.detail = detail;
        this.imagePath = getClass().getResource("/ku/cs/images/default-image.jpg").toExternalForm();
    }

    public void addTraffic(modelTraffic traffic){
        request = new UserDataSource("data/category","traffic.csv");
        request.writefile_traffic(traffic);
    }
    public int getVote() {
        return vote;
    }
    public String getLocation() {
        return location;
    }

    public String getDetailTraffic() {
        return detail;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {return imagePath;}
}
