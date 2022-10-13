package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.request.modelBuilding;
import ku.cs.services.ImageDataSource;

import java.io.File;
import java.io.IOException;

public class RequestBuildingNextController {

    @FXML private TextField equiumentTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField detailTextField;
    @FXML private ImageView requestPhoto;

    private ImageDataSource getImage;

    private String pickTarge;

    @FXML
    public void initialize(){
        requestPhoto.setImage(new Image(getClass().getResource("/ku/cs/images/default-profile.jpg").toExternalForm()));
    }

    public void handleSubmitButton(ActionEvent actionEvent) {
        try {
            modelBuilding request = new modelBuilding(0,equiumentTextField.getText(),locationTextField.getText()
                    ,detailTextField.getText(), pickTarge,"");
            request.addBuilding(request);
            com.github.saacsos.FXRouter.goTo("success_request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleUploadRequestPhotoButton(ActionEvent actionEvent){
        getImage = new ImageDataSource();
        pickTarge = getImage.chooseImage("request_building");
        File destDir = new File("image_user" + System.getProperty("file.separator") + "request_building" + System.getProperty("file.separator") + pickTarge);
        requestPhoto.setImage(new Image(destDir.toURI().toString()));
    }

}