package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import ku.cs.models.modelRegister;
import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;
import ku.cs.models.modelUser;
import ku.cs.services.ImageDataSource;
import ku.cs.services.RequestListDataSource;

import java.io.File;
import java.io.IOException;

public class ProfileUserController {
    //FXML
    @FXML private Label nameLabel;
    @FXML private ImageView nisitPhoto;

    //Image
    private ImageDataSource getImage;
    private String pickTarget;

    //model
    private modelUser userName;
    private modelRegister user;

    /*Table*/
    @FXML private TableView<modelRequest> tableView;
    @FXML
    private TableColumn<modelRequest, String> categoryList;
    @FXML
    private TableColumn<modelRequest, Integer> scoreList;
    @FXML
    private TableColumn<modelRequest, String> timeList;
    @FXML
    private TableColumn<modelRequest, String> statusList;
    @FXML
    private TableColumn<modelRequest, String> subjectList;

    private RequestListDataSource dataSource;
    private modelRequestList requestList;
    private ObservableList<modelRequest> login_board;

    //Label
    @FXML private Label categoryLabel;
    @FXML private Label headLabel;
    @FXML private Label detailLabel;
    @FXML private Label staffdetailLabel;
    @FXML private Label text_category;
    @FXML private Label text_head;
    @FXML private Label text_detail;
    @FXML private Label text_fixStaff;

    @FXML
    public void initialize(){
        /*set User*/
        user = (modelRegister) FXRouter.getData();
        userName = new modelUser(user.getName());
        nameLabel.setText(userName.getName());
        detailLabel.setWrapText(true);
        staffdetailLabel.setWrapText(true);
        headLabel.setWrapText(true);

        /*set Image*/
        File destDir = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + user.getImagePath());
        nisitPhoto.setImage(new Image(destDir.toURI().toString()));

        /*set Table*/
        dataSource = new RequestListDataSource("data","");
        requestList = dataSource.readfileRequest(user.getName());
        login_board = FXCollections.observableArrayList();
        setMenuTable();
        loadTable();
    }
    private void setMenuTable(){
        subjectList.setCellValueFactory(new PropertyValueFactory<>("subject"));
        categoryList.setCellValueFactory(new PropertyValueFactory<>("category"));
        timeList.setCellValueFactory(new PropertyValueFactory<>("time"));
        statusList.setCellValueFactory(new PropertyValueFactory<>("status"));
        scoreList.setCellValueFactory(new PropertyValueFactory<>("vote"));
    }
    private void loadTable(){
        login_board.addAll(requestList.getAllRequest());

        tableView.setItems(login_board);
        tableView.setOnMouseClicked(e ->{
            event();
        });
    }
    private void event(){
        modelRequest user = tableView.getSelectionModel().getSelectedItem();
        if (user != null){ // มันอาจจะเป็น null
            text_category.setText("หมวดหมู่ :");
            text_head.setText("หัวเรื่อง :");
            text_detail.setText("รายละเอียด :");
            text_fixStaff.setText("วิธีการแก้ไขจากเจ้าหน้าที่ :");

            categoryLabel.setText(user.getCategory());
            headLabel.setText(user.getSubject());
            detailLabel.setText(user.getRequestDetail());
            staffdetailLabel.setText(user.getManageDetail());
        }
    }

    private void showUserName(){
        nameLabel.setText(userName.getName());
    }

    public void handleBackStartButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("start", user);

        } catch (IOException e) {
            System.err.println("ไปที่หน้า start ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public void handleBackUserButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("user", user);

        } catch (IOException e) {
            System.err.println("ไปที่หน้า user ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public void handleChangNisitPhotoButton(){
        getImage = new ImageDataSource();
        pickTarget = getImage.chooseImage("user_images");
        File destDir = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + pickTarget);
        nisitPhoto.setImage(new Image(destDir.toURI().toString()));
        user.setImagePath(pickTarget);
        user.change_image(pickTarget);
    }
}
