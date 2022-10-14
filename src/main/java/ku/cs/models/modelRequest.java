package ku.cs.models;

import ku.cs.models.request.*;

public class modelRequest {

    protected String category;
    protected String subject; //คำร้อง

    protected String userName;
    protected String status;
    protected int votePoint;
    private String detail;
    private String time;
    private String staffName;
    private String requestDetail;
    private String manageDetail;
    private String reportDetail;

    private String imagePath;
    //staff
    public modelRequest(String subject,String category, String status, String staffName){
        this.subject = subject;
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

    public modelRequest(String userName,String category,String head,String status,int votePoint){
        this.category = category;
        this.subject = head;
        this.userName = userName;
        this.status = status;
        this.votePoint = votePoint;
    }
    // admin
    public modelRequest(String realName,String subject,String category){
        this.userName = realName;
        this.subject = subject;
        this.category = category;
    }

    public String getReportDetail() {
        return reportDetail;
    }

    public String getUserName() {
        return userName;
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
}


