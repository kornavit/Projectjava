package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;
import ku.cs.services.StaffDataSource;

import java.io.IOException;

public class StaffWorking {

    @FXML
    private Label category;

    @FXML
    private Label requestName;

    @FXML
    private Label detailFromUser;

    @FXML
    private Label staffGroup;

    @FXML
    private ChoiceBox<String> status;
    @FXML
    private TextArea reportToUser;

    private String[] chooseStatus = {"ยังไม่ได้ดำเนินการ","กำลังดำเนินการ","ดำเนินการแก้ไขเรียบร้อย"};

    private StaffDataSource staffDataSource;

    private modelRequestList requestList;
    private modelRequest request;

    public void initialize(){
        request = (modelRequest) FXRouter.getData();
        status.getItems().addAll(chooseStatus);

        requestName.setText(request.getSubject());
        staffGroup.setText(request.getStaffGroup());
        category.setText(request.getCategory());
        detailFromUser.setText(request.getRequestDetail());
        status.setValue(request.getRequestStatus());
    }

    @FXML
    void handleBackButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("staff_main_menu");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า staff_main_menu ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    void handleSubmitButton(ActionEvent event) {
        request.setRequestStatus(status.getValue());
        request.setManageDetail(reportToUser.getText());
        staffDataSource = new StaffDataSource("data/staff","user_complaint");
        requestList = staffDataSource.readData();
        staffDataSource.writeData(requestList, request);
    }

}

