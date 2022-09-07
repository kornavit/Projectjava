package ku.cs.services;

import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;

public class modelRequestHardCode {
    private modelRequestList requestList;

    public modelRequestHardCode() {
        requestList = new modelRequestList();
        readData();
    }

    public void readData() {
        modelRequest test1 = new modelRequest("การจราจรในมหาวิทยาลัย","เส้นทางไม่เรียบ","6410451156 ปรริณ", "",""
                ,"081-845-8888" ,"ยังไม่ดำเนินการ");
        requestList.addRequest(test1);

    }
    public modelRequestList getRequestList() {return requestList;}
}
