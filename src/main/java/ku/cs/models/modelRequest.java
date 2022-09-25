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

    //private RequestDataSource complaint;




    public modelRequest(String name,String category, String subject, String status) {
        this.name = name;
        this.category = category;
        this.subject = subject;
        this.status = status;
    }

    /*public void add(modelRequest request){
        complaint = new RequestDataSource("data","request.csv");
        complaint.writefile_request(request);
    }*/

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private UserDataSource user;
    public void addRequestLearning(modelRequest request){  //ตัวนี้ใช้เหมือนกันหมดทุก Request น่าจะต้องต้องเขียน if() แล้ว
        user = new UserDataSource("data/category", "learning.csv"); //if
        user.writefile_request(request); //Same
    }

    public void addRequestTraffic(modelRequest request){  //ตัวนี้ใช้เหมือนกันหมดทุก Request น่าจะต้องต้องเขียน if() แล้ว
        user = new UserDataSource("data/category", "traffic.csv"); //if
        user.writefile_request(request);
    }

    public void addRequestBuilding(modelRequest request){
        user = new UserDataSource("data/category","building.csv");
        user.writefile_request(request);
    }

    public void addRequestOther(modelRequest request){
        user = new UserDataSource("data/category", "other.csv");
        user.writefile_request(request);
    }


}


