package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.modelRequest;
import ku.cs.models.request.modelBuilding;
import ku.cs.services.ImageDataSource;
import ku.cs.services.RequestListDataSource;

import java.io.File;
import java.io.IOException;

public class RequestBuildingNextController {
    @FXML private TextField equipment;
    @FXML private TextField address;
    @FXML private TextArea detail;
    @FXML private ImageView imagePath;
    private modelRequest request;
    private String pickTarget;
    private ImageDataSource getImage;

    @FXML
    public void initialize(){
        request = (modelRequest) FXRouter.getData();
        File destDir = new File("image_user" + File.separator + "request_building" +File.separator + pickTarget);
        imagePath.setImage(new Image(destDir.toURI().toString()));
    }

    @FXML
    void uploadPic(ActionEvent actionEvent){
        getImage = new ImageDataSource();
        pickTarget = getImage.chooseImage("request_building");
        System.out.println(pickTarget);
        File destDir = new File("image_user" + File.separator + "request_building" + File.separator +  pickTarget);
        imagePath.setImage(new Image(destDir.toURI().toString()));
    }
    public void handleSubmitButton(ActionEvent actionEvent) {
        modelBuilding building = new modelBuilding(equipment.getText(),address.getText(),detail.getText(),pickTarget);
        request.setBuilding(building);
        RequestListDataSource dataSource = new RequestListDataSource("data/category");
        dataSource.writeFileRequest(request);
        try {
            FXRouter.goTo("success_request");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
