package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SuccessReportController {

    @FXML
    public void initialize(){
    }
    public void handleBackToTotalComplaintsButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("total_complaint");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า total complaint ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
