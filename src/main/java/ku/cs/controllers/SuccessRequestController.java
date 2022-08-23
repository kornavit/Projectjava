package ku.cs.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SuccessRequestController {
    @FXML public void initialize(){
    }

    public void handleBackToUserButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("user");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า user ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
