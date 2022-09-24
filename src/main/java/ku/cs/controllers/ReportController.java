package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class ReportController {

    @FXML private ChoiceBox reportCatagoryChoiceBox;
    private String[] report = {"รายงานเนื้อหา","รายงานผู้ใช้"};

    @FXML private TextArea detailReportTextArea;

    @FXML public void initialize(){
        reportCatagoryChoiceBox.getItems().addAll(report);
    }

    public void handleBackReportButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("total_complaint");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า total_complaint ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleReportButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("success_report");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_report ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
