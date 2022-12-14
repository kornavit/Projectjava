package ku.cs.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.github.saacsos.FXRouter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.modelRegister;

import java.io.File;
import java.io.IOException;

public class UserController {
    @FXML private Label nameLabel;
    @FXML private ImageView nisitPhoto;

    private modelRegister user;
    @FXML public void initialize(){
        /*set User*/
        user = (modelRegister) FXRouter.getData();
        nameLabel.setText(user.getName());

        /*set Image*/
        File destDir = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + user.getImagePath());
        nisitPhoto.setImage(new Image(destDir.toURI().toString()));

        /*profile*/
        profile();
        File photo = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + user.getImagePath());
        nisitPhoto.setImage(new Image(photo.toURI().toString()));
    }

    private void showUserName(){
        nameLabel.setText(user.getName());
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
            FXRouter.goTo("request", user);

        } catch (IOException e) {
            System.err.println("ไปที่หน้า request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleTotalComplaintsButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("total_complaint", user);

        } catch (IOException e) {
            throw new RuntimeException(e);
//            System.err.println("ไปที่หน้า total_complaint ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void profile(){
        nisitPhoto.setOnMouseClicked(e ->{
            changePhoto();
        });
    }
    public void changePhoto(){
        modelRegister profile = (modelRegister) FXRouter.getData();
        try {
            FXRouter.goTo("profile_user", profile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
