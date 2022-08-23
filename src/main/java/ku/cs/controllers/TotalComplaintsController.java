package ku.cs.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class TotalComplaintsController {
    @FXML public void initialize(){
    }

    public void handleBackStartButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("start");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า start ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleBackUserButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("user");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า user ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleRequestButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
