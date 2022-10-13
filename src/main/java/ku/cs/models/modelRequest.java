package ku.cs.models;

//import ku.cs.services.RequestDataSource;

import ku.cs.services.UserDataSource;

public class modelRequest {
    protected String category;
    protected String subject; //คำร้อง
    private String realName; // ชื่อจริงๆของแต่ละ Account

    protected String userName;
    protected String status;
    protected int votePoint;
    private String detail;

    private String time;
    private String imagePath;

    private String staffName;
    private String staffGroup;

    private String requestDetail;

    private String manageDetail;



    //user_complaint

    public modelRequest(String realName, String category, String subject, String status) {
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

    //Read File Request [ALL]
    public modelRequest(String userName,String category,String subject,String status, int votePoint, String detail, String imagePath, String time) {
        this.category = category;
        this.subject = subject;
        this.userName = userName;
        this.status = status;
        this.votePoint = votePoint;
        this.detail = detail;
        this.time = time;
        this.imagePath = imagePath;
    }
    // username,category,head,status,vote
    public modelRequest(String userName,String category,String head,String status,int votePoint){
        this.category = category;
        this.subject = head;
        this.userName = userName;
        this.status = status;
        this.votePoint = votePoint;
    }

    public String getCategory() {
        return category;
    }

    public String getUserName() {
        return userName;
    }

    public String getSubject() {
        return subject;
    }

    private UserDataSource user;

    public String getRealName() {
        return realName;
    }


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getVotePoint(){
        return votePoint;
    }
    public String getTime(){
        return time;
    }
    public void setVotePoint(int votePoint) {
        this.votePoint = votePoint;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
    public void addRequestLearning(modelRequest request) {
        user = new UserDataSource("data/category", "learning.csv");
        user.writefile_request(request);
    }
    public void addRequestTraffic(modelRequest request){
        user = new UserDataSource("data/category", "traffic.csv");
        user.writefile_request(request);
    }

    public void addRequestBuilding(modelRequest request){
        user = new UserDataSource("data/category","building.csv");
        user.writefile_request(request);
    }
    public void addRequestFinance(modelRequest request){
        user = new UserDataSource("data/category", "finance.csv");
        user.writefile_request(request);
    }

    public void addRequestOther(modelRequest request){
        user = new UserDataSource("data/category", "other.csv");
        user.writefile_request(request);
    }

//    private void addToAllRequest(modelRequest request){
//        user.writefile_request(request);
//        requests = new UserDataSource("data","nisit.csv");
//        requests.writefile_request(request);
//    }



}


