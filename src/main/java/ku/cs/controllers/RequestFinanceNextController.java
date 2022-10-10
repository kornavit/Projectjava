package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.request.modelFinance;
import ku.cs.services.ImageDataSource;

import java.io.File;
import java.io.IOException;

public class RequestFinanceNextController {
    @FXML
    private TextField amountTextField;
    @FXML private TextArea detailTextArea;

    @FXML private ImageView requestImageView;



    private ImageDataSource getImage;
    private String pickTarge;

    @FXML
    public void initialize(){
        requestImageView.setImage(new Image(getClass().getResource("/ku/cs/images/default-image.jpg").toExternalForm()));
    }



    public void handleSubmitButton(ActionEvent actionEvent) {
        try {
            modelFinance request = new modelFinance(0,Integer.parseInt(amountTextField.getText()), detailTextArea.getText());
            request.setImagePath(pickTarge);
            request.addFinance(request);
            com.github.saacsos.FXRouter.goTo("success_request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleUploadRequestPhotoButton(ActionEvent actionEvent){
        getImage = new ImageDataSource();
        pickTarge = getImage.chooseImage("request_traffic");
        File destDir = new File("image_user" + System.getProperty("file.separator") + "request_traffic" + System.getProperty("file.separator") + pickTarge);
        requestImageView.setImage(new Image(destDir.toURI().toString()));
    }
}
