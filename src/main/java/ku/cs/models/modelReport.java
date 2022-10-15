package ku.cs.models;

import ku.cs.services.ReportDataSource;

public class modelReport {
    private ReportDataSource person;
    private String username;
    private String detail;
    private String head;
    private String category;

    //preBan
    public modelReport(String username) {
        this.username = username;
    }

    //deleteDetail


    public modelReport(String username, String head, String category) {
        this.username = username;
        this.head = head;
        this.category = category;
    }

    public void addPreBan(modelReport report){
        person = new ReportDataSource("data","preBan.csv");
        person.writeFilePreBan(report);
    }
    public void addDeleteDetail(modelReport report){
        person = new ReportDataSource("data","deleteDetail.csv");
        person.writeFileDeleteDetail(report);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReportDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
