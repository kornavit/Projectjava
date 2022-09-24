package ku.cs.models.request;

import ku.cs.models.modelRequest;
import ku.cs.services.UserDataSource;

public class modelLearning {
    private String course;
    private String teacher;
    private String group;
    private String detail;
    private int vote;
    private UserDataSource request;

    public modelLearning(Integer vote, String course, String teacher, String group, String detail) {
        this.vote = 0;
        this.course = course;
        this.teacher = teacher;
        this.group = group;
        this.detail = detail;
    }

    public void addLearning(modelLearning learning){
        request = new UserDataSource("data/category","learning.csv");
        request.writefile_learning2(learning);
    }

    public String getCourse() {
        return course;
    }
    public String getTeacher() {
        return teacher;
    }
    public String getGroup() {
        return group;
    }
    public String getDetail() {
        return detail;
    }
    public int getVote() {
        return vote;
    }
}
