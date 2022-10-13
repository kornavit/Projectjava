package ku.cs.models.request;

import ku.cs.services.UserDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class modelBuilding {
    private int vote;
    private String equiument;
    private String location;
    private String detail;
    private String imagePath;

    private String time;


    public modelBuilding(int vote, String equiument, String location, String detail, String imagePath, String time) {
        this.vote = vote;
        this.equiument = equiument;
        this.location = location;
        this.detail = detail;
        this.imagePath = imagePath;
    }

    public int getVote() {
        return vote;
    }

    public String getEquiument() {
        return equiument;
    }

    public String getLocation() {
        return location;
    }

    public String getDetail() {
        return detail;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
