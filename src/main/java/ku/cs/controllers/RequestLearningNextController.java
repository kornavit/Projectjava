package ku.cs.controllers;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ku.cs.models.modelRegister;
import ku.cs.models.modelRequest;
import ku.cs.models.modelUser;
import ku.cs.models.request.modelLearning;
import ku.cs.services.RequestListDataSource;

import java.io.IOException;

public class RequestLearningNextController {

    @FXML private TextField course;
    @FXML private TextField teacher;
    @FXML private TextField group;
    @FXML private TextArea detail;
    private modelRequest request;

    @FXML public void initialize(){
        request = (modelRequest) FXRouter.getData();
    }

    public void handleSubmitButton(ActionEvent actionEvent) {
        try {
            modelLearning learning = new modelLearning(course.getText(), teacher.getText(), group.getText(), detail.getText().replace("\n","|"));
            request.setLearning(learning);
            RequestListDataSource dataSource = new RequestListDataSource("data/category");
            dataSource.writeFileRequest(request); // ใน method นี้มันคือเขียนในไฟล์อะไรก็ได้น่ะ ขอแค่รับเรื่อง request มา

            FXRouter.goTo("success_request");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}