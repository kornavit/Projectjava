
package ku.cs.models.request;

import ku.cs.models.modelRequest;

public class modelTraffic {
    private String location;
    private String detail;
    private String imagePath;

    public modelTraffic(String location, String detail, String imagePath) {
        this.location = location;
        this.detail = detail;
        this.imagePath = imagePath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}