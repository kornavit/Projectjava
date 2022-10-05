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
    @FXML private TableColumn<modelRequest, String> subjectList;
    @FXML private TableColumn<modelRequest, String> categoryList;
    @FXML private TableColumn<modelRequest, Integer> scoreList;
    @FXML private TableColumn<modelRequest, String> statusList;
    @FXML private TableColumn timeList;

    @FXML private ComboBox<String> sortByComboBox;
    ObservableList<String> list = FXCollections.observableArrayList("การเรียนการสอน", "อาคาร สถานที่และสิ่งอำนวยความสะดวก", "การจราจรในมหาวิทยาลัย", "อื่นๆ");

    private modelRegister user;


    @FXML private Label categoryLabel;
    @FXML private Label nameLabel;
    @FXML private Label headLabel;
    @FXML private Label detailLabel;

    @FXML private Label usernameLabel;
    private modelUser userName;
    @FXML private ImageView nisitPhoto;

    private RequestListDataSource dataSource;



    private modelRequestList requestList;

//    ObservableList<modelRequest> observableList = FXCollections.observableArrayList(
//            modelRequest request1 = new modelRequest("123","traffic","รถจอดข้างทางบ่อย","ยังไม่ดำเนินการ");
//
//            modelRequest request2 = new modelRequest("สรณ์สิริ หงษ์ษา","Building","ทางเดินในมหาลัยขรุขระมากครับ อยากให้แก้ไขโดยเร็ว","ยังไม่ดำเนินการ");
////    );


    @FXML public void initialize(){
        modelRegister user = (modelRegister) FXRouter.getData();
        userName = new modelUser(user.getName());
        usernameLabel.setText(userName.getName());

        File destDir = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + user.getImagePath());
        nisitPhoto.setImage(new Image(destDir.toURI().toString()));

        sortByComboBox.setItems(list);



        categoryList.setCellValueFactory(new  PropertyValueFactory<modelRequest, String>("category"));
        subjectList.setCellValueFactory(new PropertyValueFactory<modelRequest, String>("subject"));
//        //scoreColumn.setCellValueFactory(new PropertyValueFactory<modelRequest, String>("voteScore"));
//        statusList.setCellValueFactory(new PropertyValueFactory<modelRequest, String>("status"));
        //timeColumn.setCellValueFactory(new PropertyValueFactory<modelRequest, String>("time"));

//        dataSource = new RequestListDataSource("data","nisit.csv");
//        requestList = dataSource.readfileRequest();

//        dataSource = new modelRequestHardCode();
//        requestList = dataSource.getRequestList();
//        ObservableList<modelRequest> requestList = tableView.getItems();
//        tableView.setItems(observableList);
        showTableView();
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

    public void handleRequestButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("request");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleReportButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("report");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า report ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    private void showTableView() {
        modelRequest request1 = new modelRequest("123","traffic","รถจอดข้างทางบ่อย","ยังไม่ดำเนินการ");

        modelRequest request2 = new modelRequest("สรณ์สิริ หงษ์ษา","Building","ทางเดินในมหาลัยขรุขระมากครับ อยากให้แก้ไขโดยเร็ว","ยังไม่ดำเนินการ");
        tableView.getItems().addAll(request1, request2);
    }

}
