package ku.cs.models.request;

import ku.cs.services.UserDataSource;

public class modelBuilding {
    private int vote;
    private String equiument;
    private String location;
    private String detail;

    private UserDataSource request;

    public modelBuilding(int vote, String equiument, String location, String detail) {
        this.vote = vote;
        this.equiument = equiument;
        this.location = location;
        this.detail = detail;
    }

    public void addBuilding(modelBuilding building){
        request = new UserDataSource("data/category","building.csv");
        request.writefile_building(building);
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
}
