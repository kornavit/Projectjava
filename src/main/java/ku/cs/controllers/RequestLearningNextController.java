package ku.cs.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ku.cs.models.request.modelLearning;

import java.io.IOException;

public class RequestLearningNextController {

    @FXML private TextField course;
    @FXML private TextField teacher;
    @FXML private TextField group;
    @FXML private TextField detail;

    @FXML public void initialize(){
    }

    public void handleSubmitButton(ActionEvent actionEvent) {
        try {
            modelLearning request = new modelLearning(0,course.getText(), teacher.getText(), group.getText(), detail.getText(), "");
            //request.addLearning(request);
            com.github.saacsos.FXRouter.goTo("success_request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}