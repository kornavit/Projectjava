package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

public class RequestDetailController {
    @FXML private ImageView like;
    @FXML private Label moreDetailLabel;
    @FXML private Label detailLabel;
    @FXML private Label nameLabel;
    @FXML private Label headLabel;
    @FXML private Label categoryLabel;
    @FXML private Label voteLabel;
    @FXML private Label nullPhotoLabel;
    @FXML private ImageView photoImageView;
    private String photoLike;
    @FXML
    public void initialize(){
        //Photo
        File destDir = new File("image_user" + System.getProperty("file.separator") + "like" + System.getProperty("file.separator") + "like_1.png");
        like.setImage(new Image(destDir.toURI().toString()));
        changeLike();
        photoLike = "like_1.png";

        //setText
        moreDetailLabel.setWrapText(true);
        detailLabel.setWrapText(true);
    }
    public void changeLike(){
        like.setOnMouseClicked(e ->{
            if (photoLike.equals("like_1.png")){
                File destDir = new File("image_user" + System.getProperty("file.separator") + "like" + System.getProperty("file.separator") + "like_2.png");
                like.setImage(new Image(destDir.toURI().toString()));
                photoLike = "like_2.png";
            }else {
                File destDir = new File("image_user" + System.getProperty("file.separator") + "like" + System.getProperty("file.separator") + "like_1.png");
                like.setImage(new Image(destDir.toURI().toString()));
                photoLike = "like_1.png";
            }
        });
    }
    public void handleReportButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("report");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า report ไม่ได้");
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
}
