package ku.cs.models.request;

import ku.cs.services.UserDataSource;

public class modelLearning {
    private String course;
    private String teacher;
    private String group;
    private String detail;
    private int vote;
    private String time;
    private UserDataSource request;


    public modelLearning(Integer vote, String course, String teacher, String group, String detail, String time) {
        this.vote = vote;
        this.course = course;
        this.teacher = teacher;
        this.group = group;
        this.detail = detail;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
