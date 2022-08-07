package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NisitRegisterController {
    @FXML private TextField nameTextField;
    @FXML private TextField passTextField;
    @FXML private TextField cpTextField;
    @FXML private TextField userTextField;
    @FXML private Label resultCheckUsername;

    @FXML public void initialize(){
    }
    public void handleCheckUsernameButton(ActionEvent actionEvent) {
    }

    public void handleUploadNisitPhotoButton(ActionEvent actionEvent) {
    }

    public void handleSubmitButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("success");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า success ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleBackNisitRegisterButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("start");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า start ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
