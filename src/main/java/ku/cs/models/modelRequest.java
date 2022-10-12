package ku.cs.models;

//import ku.cs.services.RequestDataSource;

import ku.cs.models.request.*;
import ku.cs.services.RequestListDataSource;
import ku.cs.services.UserDataSource;

public class modelRequest {
    protected String category;
    protected String subject; //คำร้อง
    protected String username; // ชื่อจริงๆของแต่ละ Account
    protected String status;
    private String staffName;
    private String staffGroup;
    private String extra; //ข้อมูลเพิ่มเติม เช่น vote,type
    private String extraDetail; // ปริ้นตรง staff

    private String time;
    protected int votePoint;
    private String[] vote;
    private String requestDetail;

    private String manageDetail;

    //composition
    protected modelBuilding building;
    protected modelFinance finance;
    protected modelLearning learning;
    protected modelOther other;
    protected modelTraffic traffic;

    public modelLearning getLearning(){
        return learning;
    }

    public modelBuilding getBuilding() {
        return building;
    }

    public modelFinance getFinance() {
        return finance;
    }

    public modelOther getOther() {
        return other;
    }

    public modelTraffic getTraffic() {
        return traffic;
    }

    public void setLearning(modelLearning learning) {
        this.learning = learning;
    }

    public void setBuilding(modelBuilding building) {
        this.building = building;
    }

    public void setFinance(modelFinance finance) {
        this.finance = finance;
    }

    public void setOther(modelOther other) {
        this.other = other;
    }

    public void setTraffic(modelTraffic traffic) {
        this.traffic = traffic;
    }

    //user_complaint
    public modelRequest(String subject, String category,String time, String status, int votePoint) {
        this.subject = subject;
        this.category = category;
        this.time = time;
        this.status = status;
        this.votePoint = votePoint;

    }

    public modelRequest(String username, String subject, String category, String status) {
        this.username = username;
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

    public modelRequest(modelRequest request) {
        this.username = request.getUsername();
        this.category = request.getCategory();
        this.subject = request.getSubject();
        this.status = request.getStatus();
        this.votePoint = request.getVotePoint();
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

    private RequestListDataSource user, requests;
    public void addRequestLearning(modelRequest request){
        user = new RequestListDataSource("data/category", "learning.csv"); //if
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getTime() {
        return time;
    }

    public int getVotePoint() {
        return votePoint;
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
        user = new RequestListDataSource("data/category", "traffic.csv"); //if
    }

    public void addRequestBuilding(modelRequest request){
        user = new RequestListDataSource("data/category","building.csv");
    }

    public void addRequestOther(modelRequest request){
        user = new RequestListDataSource("data/category", "other.csv");
    }


    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getExtraDetail() {
        return extraDetail;
    }

    public void setExtraDetail(String extraDetail) {
        this.extraDetail = extraDetail;
    }
}


