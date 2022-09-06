package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import ku.cs.models.modelRegister;
import ku.cs.services.ImageDataSource;

import java.io.IOException;


public class NisitRegisterController {
    private modelRegister selectedPhoto;


    @FXML private TextField nameTextField;
    @FXML private TextField passTextField;
    @FXML private TextField cpTextField;
    @FXML private TextField userTextField;
    @FXML private Label resultCheckUsername;
    @FXML private ImageView nisitPhoto;

    private ImageDataSource getImage;



    @FXML public void initialize(){
        nisitPhoto.setImage(new Image(getClass().getResource("/ku/cs/images/default-profile.jpg").toExternalForm()));
    }

    public void handleCheckUsernameButton(ActionEvent actionEvent) {

    }

    public void handleUploadNisitPhotoButton(ActionEvent actionEvent) {
        getImage = new ImageDataSource();
        String pic_target = getImage.chooseImage("user_images");
        nisitPhoto.setImage(new Image(pic_target));

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
