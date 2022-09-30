package ku.cs.models;

//import ku.cs.services.RequestDataSource;

public class modelRequest {
    private String category;
    private String subject; //คำร้อง
    private String realName; // ชื่อนิสิต
    private String faculty;
    private String department;
    private String telephone;
    private String requestStatus;
    private String staffName;
    private String staffGroup;

    private String requestDetail;

    private String manageDetail;



    //private time;
    //private int votePoint;

    //private RequestDataSource complaint;


    public modelRequest(String category, String subject, String realName, String faculty, String department, String telephone, String status) {
        this.category = category;
        this.subject = subject;
        this.realName = realName;
        this.faculty = faculty;
        this.department = department;
        this.telephone = telephone;
        this.requestStatus = status;
    }

    public modelRequest(String subject, String staffGroup,String category, String status, String staffName){
        this.subject = subject;
        this.staffGroup = staffGroup;
        this.category = category;
        this.requestStatus = status;
        this.staffName = staffName;
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

    public String getRequestStatus() {
        return requestStatus;
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

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
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

    @Override
    public String toString() {
        return "modelRequest{" +
                "category='" + category + '\'' +
                ", subject='" + subject + '\'' +
                ", name='" + realName + '\'' +
                ", requestStatus='" + requestStatus + '\'' +
                ", staffName='" + staffName + '\'' +
                ", staffGroup='" + staffGroup + '\'' +
                ", requestDetail='" + requestDetail + '\'' +
                ", manageDetail='" + manageDetail + '\'' +
                '}';
    }
}


