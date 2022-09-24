package ku.cs.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.github.saacsos.FXRouter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.modelRegister;
import ku.cs.models.modelUser;

import java.io.File;
import java.io.IOException;

public class UserController {
    @FXML private Label nameLabel;
    @FXML private ImageView nisitPhoto;

    private modelUser userName;
    @FXML public void initialize(){
        /*set User*/
        modelRegister user = (modelRegister) FXRouter.getData();
        userName = new modelUser(user.getName());
        nameLabel.setText(userName.getName());
//        System.out.println(user.getName());

        /*set Image*/
        File destDir = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + user.getImagePath());
        nisitPhoto.setImage(new Image(destDir.toURI().toString()));
    }

    private void showUserName(){
        nameLabel.setText(userName.getName());
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
            FXRouter.goTo("request", userName);

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
