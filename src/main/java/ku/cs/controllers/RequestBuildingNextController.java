package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ku.cs.models.request.modelBuilding;

import java.io.IOException;

public class RequestBuildingNextController {

    @FXML private TextField equiumentTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField detailTextField;

    @FXML
    public void initialize(){
    }

    public void handleSubmitButton(ActionEvent actionEvent) {
        try {
            modelBuilding request = new modelBuilding(0,equiumentTextField.getText(),locationTextField.getText()
                                                        ,detailTextField.getText());
            request.addBuilding(request);
            com.github.saacsos.FXRouter.goTo("success_request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleBackRequestButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
