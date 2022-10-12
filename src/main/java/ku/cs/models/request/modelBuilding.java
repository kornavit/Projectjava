package ku.cs.models.request;

import ku.cs.models.modelRequest;

public class modelBuilding {
    private String equipment;
    private String location;
    private String detail;
    private String imagePath;

    public modelBuilding(modelRequest request, String equipment, String location, String detail, String imagePath) {
        this.equipment = equipment;
        this.location = location;
        this.detail = detail;
        this.imagePath = imagePath;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
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
