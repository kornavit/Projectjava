package ku.cs.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class UserController {
    @FXML private Label nameLabel;

    @FXML public void initialize(){
    }

    public void handleBackStartButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("start");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า start ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleRequestButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleTotalComplaintsButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("total_complaint");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า total_complaint ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}