package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.modelRegister;
import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;
import ku.cs.services.ImageDataSource;
import ku.cs.services.StaffDataSource;
import ku.cs.services.UserDataSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class StaffMainMenuController implements Initializable {

    private String target;
    private ImageDataSource image;
    @FXML
    private ImageView staffProfile;
    @FXML private Label staff;
    @FXML private Label team;

    @FXML private TableView<modelRequest> menuTable;

    @FXML private TableColumn<modelRequest, String> request;

    @FXML private TableColumn<modelRequest, String> staffTeam;
    @FXML private TableColumn<modelRequest, String> category;
    @FXML private TableColumn<modelRequest, String> requestStatus;
    @FXML private TableColumn<modelRequest, String> staffName;
    private ObservableList<modelRequest> dataRequestList;
    private modelRequestList requestList;

    private modelRegister staffLogin;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staffLogin = (modelRegister) FXRouter.getData();
        staff.setText(staffLogin.getName());
        team.setText(staffLogin.getCategory());

        StaffDataSource dataSource = new StaffDataSource("data/category",staffLogin.getCategory() + ".csv");
        // requestList = dataSource.readData();
        dataRequestList = FXCollections.observableArrayList();
        setMenuTable();
        loadTable();
        event();
    }

    private void setMenuTable(){
        request.setCellValueFactory(new PropertyValueFactory<>("subject"));
        staffTeam.setCellValueFactory(new PropertyValueFactory<>("staffGroup"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        requestStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        staffName.setCellValueFactory(new PropertyValueFactory<>("staffName"));
    }

    private void event(){
        modelRequest request = menuTable.getSelectionModel().getSelectedItem();
        if (request != null){
            try {
                request.setStaffName(staffLogin.getName());
                FXRouter.goTo("staff_working", request); //go to Staff Working

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadTable(){
        dataRequestList.addAll(requestList.getAllRequest());
        menuTable.setItems(dataRequestList);
        menuTable.setOnMouseClicked(e ->{
            event();
        });
    }
    @FXML public void handleChangeProfilePic(ActionEvent actionEvent) {
        UserDataSource data = new UserDataSource("data","user.csv");
        image = new ImageDataSource();
        target = image.chooseImage("user_images");
        if(target.equals("")){
            return;
        }
        File destDir = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + target);
        staffProfile.setImage(new Image(destDir.toURI().toString()));
        staffLogin.setImagePath(target);
        data.writeData(data.readData(),staffLogin);
    }

    @FXML public void handleChangePass(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staff_change_password");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า change password ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void ExitBtn(){
        try {
            com.github.saacsos.FXRouter.goTo("start");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า start ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
