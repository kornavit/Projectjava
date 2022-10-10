package ku.cs.controllers;
import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.modelRegister;
import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;
import ku.cs.models.modelUser;
import ku.cs.services.RequestListDataSource;


import java.io.File;
import java.io.IOException;

public class TotalRequestsController {

    @FXML private TableView<modelRequest> tableView;
    @FXML private TableColumn<modelRequest, String> subjectColumn;
    @FXML private TableColumn<modelRequest, String> categoryColumn;
    @FXML private TableColumn<modelRequest, Integer> voteColumn;
    @FXML private TableColumn<modelRequest, String> statusColumn;
    @FXML private TableColumn<modelRequest, String> timeColumn;

    @FXML private ComboBox<String> sortByComboBox;
    ObservableList<String> list = FXCollections.observableArrayList("ยังไม่ถูกจัดการ", "กำลังดำเนินการ", "ถูกดำเนินการแล้ว");
    @FXML private Label usernameLabel;
    private modelUser userName;
    @FXML private ImageView nisitPhoto;

    private RequestListDataSource dataSource;

    private ObservableList<modelRequest> dataRequestList;
    private modelRequestList requestList;


    @FXML public void initialize(){
        sortByComboBox.setItems(list);
        modelRegister user = (modelRegister) FXRouter.getData();
        userName = new modelUser(user.getName());
        usernameLabel.setText(userName.getName());

        File destDir = new File("image_user" + File.separator + "user_images" + File.separator + user.getImagePath());
        nisitPhoto.setImage(new Image(destDir.toURI().toString()));
        dataSource = new RequestListDataSource();
        requestList = dataSource.readfileRequest();
        dataRequestList = FXCollections.observableArrayList();
        setTableColumn();
        loadTable();
    }

    public void handleBackStartButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("start");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า start ไม่ได้");
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

    private void loadTable(){
        dataRequestList.addAll(requestList.getAllRequest());
        tableView.setItems(dataRequestList);
    }
    private void setTableColumn(){
        categoryColumn.setCellValueFactory(new  PropertyValueFactory<>("category"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        voteColumn.setCellValueFactory(new PropertyValueFactory<>("votePoint"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
}
