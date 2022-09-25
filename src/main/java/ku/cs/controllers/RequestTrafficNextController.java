package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ku.cs.models.request.modelTraffic;

import java.io.IOException;

public class RequestTrafficNextController {

    @FXML private TextField location;
    @FXML private TextField detail;

    @FXML
    public void initialize(){
    }

    public void handleSubmitButton(ActionEvent actionEvent) {
        try {
            modelTraffic request = new modelTraffic(0,location.getText(), detail.getText());
            request.addTraffic(request);
            com.github.saacsos.FXRouter.goTo("success_request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleBackRequestButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
