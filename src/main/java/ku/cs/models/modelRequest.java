package ku.cs.models;

//import ku.cs.services.RequestDataSource;

import ku.cs.services.UserDataSource;

public class modelRequest {
    private String category;
    private String subject; //คำร้อง
    private String realName; // ชื่อจริงๆของแต่ละ Account
    private String faculty;
    private String department;
    private String telephone;
    private String status;
    private String staffName;
    private String staffGroup;

    private String requestDetail;

    private String manageDetail;



    //user_complaint

    public modelRequest(String realName, String subject, String category, String status) {
        this.realName = realName;
        this.subject = subject;
        this.category = category;
        this.status = status;
    }

    //staff
    public modelRequest(String subject, String staffGroup,String category, String status, String staffName){
        this.subject = subject;
        this.staffGroup = staffGroup;
        this.category = category;
        this.status = status;
        this.staffName = staffName;
    }

    /*public void add(modelRequest request){
        complaint = new RequestDataSource("data","request.csv");
        complaint.writefile_request(request);
    }*/


    public String getCategory() {
        return category;
    }

    public String getSubject() {
        return subject;
    }

    private UserDataSource user, requests;
    public void addRequestLearning(modelRequest request){
        user = new UserDataSource("data/category", "learning.csv"); //if
        addToAllRequest(request);
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestDetail() {
        return requestDetail;
    }

    public void setRequestDetail(String requestDetail) {
        this.requestDetail = requestDetail;
    }



    public String getManageDetail() {
        return manageDetail;
    }

    public void setManageDetail(String manageDetail) {
        this.manageDetail = manageDetail;
    }



    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffGroup() {
        return staffGroup;
    }

    public void setStaffGroup(String staffGroup) {
        this.staffGroup = staffGroup;
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


