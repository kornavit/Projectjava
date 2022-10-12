package ku.cs.models.request;

import ku.cs.models.modelRequest;

public class modelFinance {
    private int type;
    private String detail;

    public modelFinance(modelRequest request, int type, String detail) {
        this.type = type;
        this.detail = detail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
