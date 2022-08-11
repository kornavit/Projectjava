package ku.cs.models;

public class modelNisitRequest {
    private String name;

    private String request;

    public modelNisitRequest(String name, String request) {
        this.name = name;
        this.request = request;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

}
