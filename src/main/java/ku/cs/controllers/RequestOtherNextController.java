package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ku.cs.models.modelRequest;
import ku.cs.models.request.modelOther;
import ku.cs.services.RequestListDataSource;

import java.io.IOException;

public class RequestOtherNextController {
    @FXML private TextArea detail;
    private modelRequest request;

    @FXML
    public void initialize(){
        request = (modelRequest) FXRouter.getData();
    }

    public void handleSubmitButton(ActionEvent actionEvent) {
        modelOther other = new modelOther(detail.getText());
        request.setOther(other);

        RequestListDataSource dataSource = new RequestListDataSource("data/category");
        dataSource.writeFileRequest(request);
        try {
            FXRouter.goTo("success_request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
