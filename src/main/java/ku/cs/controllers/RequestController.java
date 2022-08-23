package ku.cs.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import com.github.saacsos.FXRouter;

import java.io.IOException;

public class RequestController {
    @FXML private ChoiceBox complaintCategoryChoiceBox;
    @FXML private TextField headTextField;
    @FXML private TextArea bodyTextArea;

    @FXML public void initialize(){
    }
    public void handleBackUserButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("user");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า user ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleNextButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("request_next");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า request_next ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



}
