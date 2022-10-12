package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import ku.cs.models.modelReport;
import ku.cs.models.modelRequest;
import ku.cs.models.modelUser;

import java.io.IOException;

public class ReportController {

    @FXML private ChoiceBox reportCatagoryChoiceBox;
    private String[] report = {"รายงานเนื้อหา","รายงานผู้ใช้"};
    @FXML private TextArea detailReportTextArea;

    private modelRequest request;
    @FXML public void initialize(){
        reportCatagoryChoiceBox.getItems().addAll(report);
        request = (modelRequest) FXRouter.getData();
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
            if (reportCatagoryChoiceBox.getValue().equals("รายงานเนื้อหา")){
                modelReport reportDetail = new modelReport(request.getUsername(),request.getSubject(),request.getCategory());
                reportDetail.setDetail(detailReportTextArea.getText().replace("\n","|"));
                reportDetail.addDeleteDetail(reportDetail);
            }
            else if (reportCatagoryChoiceBox.getValue().equals("รายงานผู้ใช้")){
                modelReport reportPreBan = new modelReport(request.getUsername());
                reportPreBan.setDetail(detailReportTextArea.getText().replace("\n","|"));
                reportPreBan.addPreBan(reportPreBan);
            }
            FXRouter.goTo("success_report");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_report ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
