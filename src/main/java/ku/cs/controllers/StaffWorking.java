package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;
import ku.cs.services.AlertService;
import ku.cs.services.StaffDataSource;

import java.io.File;
import java.io.IOException;

public class StaffWorking {

    @FXML
    private Label category;

    @FXML
    private Label head;

    @FXML
    private Label detailFromUser;

    @FXML
    private TextArea reportToUser;
    @FXML private Label caution;
    @FXML
    private ImageView requestPicture;
    @FXML
    private Label extra;
    @FXML
    private ChoiceBox<String> status;

    private String[] chooseStatus = {"ยังไม่ดำเนินการ","กำลังดำเนินการ","ถูกดำเนินการแล้ว"};

    private StaffDataSource staffDataSource;

    private modelRequestList requestList;
    private modelRequest request;

    private AlertService alertService;

    public void initialize(){
        request = (modelRequest) FXRouter.getData();
        //System.out.println(request.getStaffName());
        status.getItems().addAll(chooseStatus);

        head.setText(request.getSubject());
        extra.setText(request.getExtraDetail());
        category.setText(request.getCategory());
        reportToUser.setText(request.getReport());
        //System.out.println(request.getReport());

        head.setWrapText(true);
        extra.setWrapText(true);
        detailFromUser.setWrapText(true);

        detailFromUser.setText(request.getRequestDetail());
        //System.out.println(request.getRequestDetail());
        status.setValue(request.getStatus());

        if (request.getCategory().equals("building")){
            File destDir = new File("image_user" + System.getProperty("file.separator") + "request_building" + System.getProperty("file.separator") + request.getImagePath());
            requestPicture.setImage(new Image(destDir.toURI().toString()));
        }
        else if (request.getCategory().equals("traffic")){
            File destDir = new File("image_user" + System.getProperty("file.separator") + "request_traffic" + System.getProperty("file.separator") + request.getImagePath());
            requestPicture.setImage(new Image(destDir.toURI().toString()));
        }

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
        request.setStatus(status.getValue());
        if (reportToUser.getText() == null){
            caution.setTextFill(Color.RED);
            caution.setText("อย่าลืมใส่รายละเอียดการจัดการ");
            return;
        }
        request.setReport(reportToUser.getText().replace("\n","|"));
        staffDataSource = new StaffDataSource("data/category",request.getCategory() + ".csv");
        requestList = staffDataSource.readData();
        staffDataSource.writeData(requestList, request,request.getStaffName(),status.getValue());
        alertService = new AlertService();
        alertService.alertInformation();
    }
}

