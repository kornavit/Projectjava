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
import ku.cs.models.modelRegister;
import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;
import ku.cs.services.ImageDataSource;
import ku.cs.services.RequestListDataSource;
import ku.cs.services.UserDataSource;

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
    private modelRegister user;

    private UserDataSource userDataSource;

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
        userDataSource = new UserDataSource("data","user.csv");
        nameLabel.setText(user.getName());
        detailLabel.setWrapText(true);
        staffdetailLabel.setWrapText(true);
        headLabel.setWrapText(true);

        /*set Image*/
        File destDir = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + user.getImagePath());
        nisitPhoto.setImage(new Image(destDir.toURI().toString()));

        /*set Table*/
        dataSource = new RequestListDataSource("data");
        requestList = dataSource.readData(user.getUsername());
        dataSource.sortTime(requestList,"?????????????????????????????????????????????");
        login_board = FXCollections.observableArrayList();
        setMenuTable();
        loadTable();
    }
    private void setMenuTable(){
        subjectList.setCellValueFactory(new PropertyValueFactory<>("subject"));
        categoryList.setCellValueFactory(new PropertyValueFactory<>("category"));
        timeList.setCellValueFactory(new PropertyValueFactory<>("time"));
        statusList.setCellValueFactory(new PropertyValueFactory<>("status"));
        scoreList.setCellValueFactory(new PropertyValueFactory<>("votePoint"));
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
        if (user != null){ // ???????????????????????????????????? null
            text_category.setText("???????????????????????? :");
            text_head.setText("??????????????????????????? :");
            text_detail.setText("?????????????????????????????? :");
            text_fixStaff.setText("?????????????????????????????????????????????????????????????????????????????? :");

            categoryLabel.setText(user.getCategory());
            headLabel.setText(user.getSubject());
            detailLabel.setText(user.getRequestDetail().replace("|","\n"));
            if (user.getManageDetail().equals("-")){
                staffdetailLabel.setText("??????????????????????????????????????????????????????????????????????????????????????????????????????");
            }else {
                staffdetailLabel.setText(user.getManageDetail().replace("|","\n"));
            }
        }
    }

    public void handleBackStartButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("start", user);

        } catch (IOException e) {
            System.err.println("??????????????????????????? start ??????????????????");
            System.err.println("?????????????????????????????????????????????????????? route");
        }
    }
    public void handleBackUserButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("user", user);

        } catch (IOException e) {
            System.err.println("??????????????????????????? user ??????????????????");
            System.err.println("?????????????????????????????????????????????????????? route");
        }
    }
    public void handleChangNisitPhotoButton(){
        getImage = new ImageDataSource();
        pickTarget = getImage.chooseImage("user_images");
        File destDir = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + pickTarget);
        nisitPhoto.setImage(new Image(destDir.toURI().toString()));
        user.setImagePath(pickTarget);
        userDataSource.writeImage(userDataSource.readData(),user);
    }
}
