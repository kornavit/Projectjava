package ku.cs.models.request;

import ku.cs.models.modelRequest;
import ku.cs.services.RequestListDataSource;
import ku.cs.services.UserDataSource;

public class modelLearning{
    private String course;
    private String teacher;
    private String group;
    private String detail;

    public modelLearning(String course, String teacher, String group, String detail) {
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
}
