package ku.cs.controllers;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SuccessRequestController {
    @FXML public void initialize(){
    }

    public void handleBackToUserButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("user");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า user ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
