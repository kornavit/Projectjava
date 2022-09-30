package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ku.cs.models.request.modelOther;

import java.io.IOException;

public class RequestOtherNextController {

    @FXML private TextField detailTextField;

    @FXML
    public void initialize(){
    }

    public void handleSubmitButton(ActionEvent actionEvent) {
        try {
            modelOther request = new modelOther(0,detailTextField.getText());
            request.addOther(request);
            com.github.saacsos.FXRouter.goTo("success_request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
