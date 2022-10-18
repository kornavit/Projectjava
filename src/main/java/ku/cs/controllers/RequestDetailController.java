package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.modelRequest;
import ku.cs.services.RequestListDataSource;

import java.io.File;
import java.io.IOException;

public class RequestDetailController {
    @FXML private ImageView like;
    @FXML private Label moreDetailLabel;
    @FXML private Label detailLabel;
    @FXML private Label staffDetailLabel;
    @FXML private Label nameLabel;
    @FXML private Label headLabel;
    @FXML private Label categoryLabel;
    @FXML private Label voteLabel;
    @FXML private ImageView photoImageView;
    @FXML private Label showNoImage;
    private String photoLike;
    private modelRequest request;
    private RequestListDataSource dataSource;
    @FXML
    public void initialize(){
        dataSource = new RequestListDataSource();
        request = (modelRequest) FXRouter.getData();
        if (dataSource.checkLike(request,request.getGuest())){ // true is like | false is unlike
            File destDir = new File("image_user" + System.getProperty("file.separator") + "like" + System.getProperty("file.separator") + "like_2.png");
            like.setImage(new Image(destDir.toURI().toString()));
            photoLike = "like";
        }else{
            File destDir = new File("image_user" + System.getProperty("file.separator") + "like" + System.getProperty("file.separator") + "like_1.png");
            like.setImage(new Image(destDir.toURI().toString()));
            photoLike = "unlike";
        }

        //setText
        moreDetailLabel.setWrapText(true);
        detailLabel.setWrapText(true);
        staffDetailLabel.setWrapText(true);

        detailLabel.setText(request.getDetail().replace("|","\n"));
        moreDetailLabel.setText(request.getExtra());
        if (request.getManageDetail().equals("-")){
            staffDetailLabel.setText("ยังไม่มีเจ้าหน้าที่จัดการเรื่องนี้");
        }else{
            staffDetailLabel.setText(request.getManageDetail().replace("|","\n"));
        }
        nameLabel.setText(request.getUsername());
        headLabel.setText(request.getSubject());
        categoryLabel.setText(request.getCategory());
        voteLabel.setText(String.valueOf(request.getVotePoint()));
        if (request.getCategory().equals("building") || request.getCategory().equals("traffic")){
            File imageCategory = new File("image_user" + System.getProperty("file.separator") + "request_" + request.getCategory() +System.getProperty("file.separator") + request.getImagePath());
            photoImageView.setImage(new Image(imageCategory.toURI().toString()));
            showNoImage.setVisible(false);
        }
        changeLike();
    }
    public void changeLike(){
        like.setOnMouseClicked(e ->{
            if (photoLike.equals("unlike")){
                File destDir = new File("image_user" + System.getProperty("file.separator") + "like" + System.getProperty("file.separator") + "like_2.png");
                like.setImage(new Image(destDir.toURI().toString()));
                voteLabel.setText(String.valueOf(dataSource.writeVote(dataSource.readAdminRequest(request.getCategory()),request,request.getGuest(),"like")));
                photoLike = "like";
            }else {
                File destDir = new File("image_user" + System.getProperty("file.separator") + "like" + System.getProperty("file.separator") + "like_1.png");
                like.setImage(new Image(destDir.toURI().toString()));
                voteLabel.setText(String.valueOf(dataSource.writeVote(dataSource.readAdminRequest(request.getCategory()),request,request.getGuest(),"unlike")));
                photoLike = "unlike";
            }
        });
    }
    public void handleReportButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("report",request);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า report ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleBackTotalRequestButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("total_complaint");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า total_complaint ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
