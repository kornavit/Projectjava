package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SuccessController {
    @FXML
    public void initialize(){
    }
    public void handleBackToLoginButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("start");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า start ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
