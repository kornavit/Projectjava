package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class RequestFinanceNextController {
    @FXML
    public void initialize(){
    }
    public void handleSubmitButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("success_request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
