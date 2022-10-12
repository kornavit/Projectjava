package ku.cs.models.request;

import ku.cs.models.modelRequest;

public class modelOther {
    private String detail;

    public modelOther(modelRequest request, String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
