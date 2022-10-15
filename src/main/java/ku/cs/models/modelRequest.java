package ku.cs.models;

import ku.cs.services.UserDataSource;

public class modelRequest {
    private String subject; //คำร้อง
    private String category; //หมวดหมู่
    private String username; // ชื่อผู้ใช้จริงๆของแต่ละ Account ในระบบ username
    private String status; //สถานะ
    private String[] vote; //คนที่มาโหวต
    private int votePoint; //จำนวนคนที่มากดไลค์
    private String extra; //ข้อมูลเพิ่มเติม เช่น vote,type
    private String extraDetail; // แสดงข้อมูลเพิ่มเติมออกมา

    private String staffName; //ชื่อเจ้าหน้าที่

    private String requestDetail; //รายละเอียดคำร้อง

    private String report; //รายละเอียดการจัดการคำร้อง

    private String time;

    private String imagePath;

    private UserDataSource user, requests;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public modelRequest(String username, String subject, String category, String status, int vote) {
        this.username = username;
        this.subject = subject;
        this.category = category;
        this.status = status;
        this.votePoint = vote;
    }

    //staff-working
    public modelRequest(String category, String subject, String status){
        this.category = category;
        this.subject = subject;
        this.status = status;
    }

    public String getVote() {
        String userVote = "";
        for (String s : vote){
            userVote += s + "|";
        }
        return userVote;
    }
    public void setVote(String[] vote) {
        this.vote = vote;
    }
    public String getExtraDetail() {
        return extraDetail;
    }

    public void setExtraDetail(String extraDetail) {
        this.extraDetail = extraDetail;
    }


    public int getVotePoint() {
        return votePoint;
    }

    public void setVotePoint(int votePoint) {
        this.votePoint = votePoint;
    }

    public String getCategory() {
        return category;
    }


    public String getSubject() {
        return subject;
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void addRequestLearning(modelRequest request){
        user = new UserDataSource("data/category", "learning.csv");
        addToAllRequest(request);
    }

    public void addRequestTraffic(modelRequest request){
        user = new UserDataSource("data/category", "traffic.csv");
        addToAllRequest(request);
    }

    public void addRequestBuilding(modelRequest request){
        user = new UserDataSource("data/category","building.csv");
        addToAllRequest(request);
    }
    public void addRequestFinance(modelRequest request){
        user = new UserDataSource("data/category", "finance.csv");
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


