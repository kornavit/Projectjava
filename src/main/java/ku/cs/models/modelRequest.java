package ku.cs.models;

//import ku.cs.services.RequestDataSource;

import ku.cs.services.UserDataSource;

public class modelRequest {
    private String category;
    private String subject;
    private String name;
    private String status;

    //private time;
    //private int votePoint;





    public modelRequest(String name,String category, String subject, String status) {
        this.name = name;
        this.category = category;
        this.subject = subject;
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public String getSubject() {
        return subject;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    private UserDataSource user, requests;
    public void addRequestLearning(modelRequest request){
        user = new UserDataSource("data/category", "learning.csv"); //if
        addToAllRequest(request);
    }

    public void addRequestTraffic(modelRequest request){
        user = new UserDataSource("data/category", "traffic.csv"); //if
        addToAllRequest(request);
    }

    public void addRequestBuilding(modelRequest request){
        user = new UserDataSource("data/category","building.csv");
        addToAllRequest(request);
    }

    public void addRequestOther(modelRequest request){
        user = new UserDataSource("data/category", "other.csv");
        addToAllRequest(request);
    }

    public void addToAllRequest(modelRequest request){
        user.writefile_request(request);
        requests = new UserDataSource("data","nisit.csv");
        requests.writefile_request(request);
    }



}


