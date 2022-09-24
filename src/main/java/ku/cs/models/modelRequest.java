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
    public void addLearning(modelRequest request){
        user = new UserDataSource("data/category", "learning.csv");
        user.writefile_learning1(request);
    }


}


