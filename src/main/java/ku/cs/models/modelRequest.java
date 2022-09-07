package ku.cs.models;

//import ku.cs.services.RequestDataSource;

public class modelRequest {
    private String category;
    private String subject;
    private String name;
    private String faculty;
    private String department;
    private String telephone;
    private String status;

    //private time;
    //private int votePoint;

    //private RequestDataSource complaint;




    public modelRequest(String category, String subject, String name, String faculty, String department, String telephone, String status) {
        this.category = category;
        this.subject = subject;
        this.name = name;
        this.faculty = faculty;
        this.department = department;
        this.telephone = telephone;
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
}


