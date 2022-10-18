package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SuccessReportController {

    @FXML
    public void initialize(){
    }
    public void handleBackToTotalComplaintsButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("total_complaint");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า total complaint ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
