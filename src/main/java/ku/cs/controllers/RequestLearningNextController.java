package ku.cs.controllers;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ku.cs.models.modelRequest;
import ku.cs.models.request.modelLearning;

import java.io.IOException;

public class RequestLearningNextController {

    @FXML private TextField course;
    @FXML private TextField teacher;
    @FXML private TextField group;
    @FXML private TextField detail;
    private RequestController requestUser;
    private modelRequest Request;

    @FXML public void initialize(){
    }

    public void handleSubmitButton(ActionEvent actionEvent) {
        try {
            modelRequest request = new modelRequest(Request.getSubject(),Request.getCategory(),Request.getTime(),Request.getStatus(),Request.getVotePoint());

            modelLearning learning = new modelLearning(request,course.getText(), teacher.getText(), group.getText(), detail.getText());
//            request.addLearning(request);
            FXRouter.goTo("success_request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
