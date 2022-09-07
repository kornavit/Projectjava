package ku.cs.models;

import java.util.ArrayList;

public class modelRequestList {
    private ArrayList<modelRequest> requests;
    public modelRequestList() {
        requests = new ArrayList<>();
    }

    public void addRequest(modelRequest request) {
        requests.add(request);
    }

    public ArrayList<modelRequest> getAllRequest() {
        return requests;
    }
}
