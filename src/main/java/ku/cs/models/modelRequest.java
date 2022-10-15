package ku.cs.models;

import ku.cs.models.request.*;

import ku.cs.models.request.*;
import ku.cs.services.RequestListDataSource;
import ku.cs.services.UserDataSource;

public class modelRequest {
    protected String category;
    protected String subject; //คำร้อง
    protected String status;
    private String username; // ชื่อจริงๆของแต่ละ Account
    protected int votePoint;
    private String[] vote;
    private String detail;
    private String time;
    private String staffName;
    private String requestDetail;
    private String manageDetail;
    private String reportDetail;
    private String imagePath;
    private String extra; //ข้อมูลเพิ่มเติม เช่น vote,type
    private String extraDetail; // ปริ้นตรง staff

    private String guest;

    //composition
    protected modelBuilding building;
    protected modelFinance finance;
    protected modelLearning learning;
    protected modelOther other;
    protected modelTraffic traffic;

    //staff
    public modelRequest(String subject,String category, String status, String staffName){
        this.subject = subject;
        this.category = category;
        this.status = status;
        this.staffName = staffName;
    }

    //Read File Request [ALL]
    public modelRequest(String username,String category,String subject,String status, int votePoint, String detail, String imagePath, String time) {
        this.category = category;
        this.subject = subject;
        this.username = username;
        this.status = status;
        this.votePoint = votePoint;
        this.detail = detail;
        this.time = time;
        this.imagePath = imagePath;
    }


    // request
    public modelRequest(String username,String head,String time,String status,int votePoint){
        this.subject = head;
        this.username = username;
        this.status = status;
        this.votePoint = votePoint;
        this.time = time;
    }

    public modelRequest(modelRequest request) {
        this.username = request.getUsername();
        this.category = request.getCategory();
        this.subject = request.getSubject();
        this.status = request.getStatus();
        this.votePoint = request.getVotePoint();
    }
    // admin
    public modelRequest(String username,String subject,String category){
        this.username = username;
        this.subject = subject;
        this.category = category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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


    public String getReportDetail() {
        return reportDetail;
    }

    public String getUsername() {
        return username;
    }
    public void setReportDetail(String reportDetail) {
        this.reportDetail = reportDetail;
    }

    public String getCategory() {return category;}

    public String getSubject() {return subject;}

    public void setSubject(String subject) {this.subject = subject;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public String getRequestDetail() {return requestDetail;}
    public void setRequestDetail(String requestDetail) {this.requestDetail = requestDetail;}
    public String getManageDetail() {return manageDetail;}
    public void setManageDetail(String manageDetail) {this.manageDetail = manageDetail;}
    public String getStaffName() {return staffName;}
    public void setStaffName(String staffName) {this.staffName = staffName;}

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
    @Override
    public String toString() {
        return subject;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }
}


