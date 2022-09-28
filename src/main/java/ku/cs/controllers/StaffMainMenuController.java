package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import ku.cs.models.modelNisitRequest;

import java.net.URL;
import java.util.ResourceBundle;


public class StaffMainMenuController implements Initializable {
    @FXML
    private ImageView logo;

    @FXML
    private TableView<modelNisitRequest> menuTable;

    @FXML
    private TableColumn<modelNisitRequest, String> name;

    @FXML
    private TableColumn<modelNisitRequest, String> request;
    @FXML
    private TextField studentName;

    @FXML
    private TextField studentRequest;


    ObservableList<modelNisitRequest> observableList = FXCollections.observableArrayList(
            new modelNisitRequest("สมชาย รักการเขียนโปรแกรม","อยากให้อัพเดตตารางสอบในเว็บ my.ku.th ครับ"),

            new modelNisitRequest("สรณ์สิริ หงษ์ษา","ทางเดินในมหาลัยขรุขระมากครับ อยากให้แก้ไขโดยเร็ว")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<modelNisitRequest, String>("name"));
        request.setCellValueFactory(new PropertyValueFactory<modelNisitRequest, String>("request"));
        menuTable.setItems(observableList);
    }

    @FXML
    public void initialize() {
        String url = getClass().getResource("/ku/cs/images/KU_Logo_PNG.png").toExternalForm();
        logo.setImage(new Image(url));
    }

    @FXML
    public void ConfirmBtn(ActionEvent actionEvent){
    }
    @FXML
    public void ExitBtn(){

    }


}
