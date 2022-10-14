package ku.cs.controllers;
import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import ku.cs.models.modelRegister;
import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;
import ku.cs.models.modelUser;
import ku.cs.services.RequestListDataSource;
import ku.cs.services.TimeComparator;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class TotalRequestsController {

    @FXML private Label warningStatus;
    @FXML private TextField moreThanVoteTextField;
    @FXML private TextField untilTextField;
    @FXML private TextField toTextField;

    @FXML private TableView<modelRequest> tableView;
    @FXML private TableColumn<modelRequest, String> subjectColumn;
    @FXML private TableColumn<modelRequest, String> categoryColumn;
    @FXML private TableColumn<modelRequest, Integer> voteColumn;
    @FXML private TableColumn<modelRequest, String> statusColumn;
    @FXML private TableColumn<modelRequest, String> timeColumn;

    @FXML private ComboBox<String> sortStatusComboBox;
    @FXML private ComboBox<String> sortTimeComboBox;
    @FXML private ComboBox<String> sortVoteComboBox;
    @FXML private ComboBox<String> sortCategoryComboBox;
    ObservableList<String> sortByList = FXCollections.observableArrayList("ยังไม่ดำเนินการ", "กำลังดำเนินการ", "ถูกดำเนินการแล้ว");
    ObservableList<String> sortTimeList = FXCollections.observableArrayList("ล่าสุดไปเก่าสุด", "เก่าสุดไปล่าสุด");
    ObservableList<String> sortVoteList = FXCollections.observableArrayList("มากสุดไปน้อยสุด", "น้อยสุดไปมากสุด");
    ObservableList<String> sortCategoryList = FXCollections.observableArrayList("การเรียนการสอน", "อาคาร สถานที่และสิ่งอำนวยความสะดวก", "การจราจรในมหาวิทยาลัย","การเงินในมหาวิทยาลัย", "อื่นๆ");

    private RequestListDataSource dataSource;

    private ObservableList<modelRequest> dataRequestList;
    private modelRequestList requestList;

    private modelRegister user;

    @FXML public void initialize(){

        sortStatusComboBox.setItems(sortByList);
        sortTimeComboBox.setItems(sortTimeList);
        sortVoteComboBox.setItems(sortVoteList);
        sortCategoryComboBox.setItems(sortCategoryList);


        user = (modelRegister) FXRouter.getData();
        dataSource = new RequestListDataSource();
        requestList = dataSource.readfileRequest();
        dataRequestList = FXCollections.observableArrayList();
        setTableColumn();
        loadTable(requestList);
        event();

    }

    private void event(){
        modelRequest request = tableView.getSelectionModel().getSelectedItem();
        if (request != null){
            try {
//                System.out.println(request.getUsername());
//                System.out.println(request.getCategory());
                request.setGuest(user.getUsername());
                FXRouter.goTo("request_detail",request);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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

    private void loadTable(modelRequestList requestList){
        dataRequestList.addAll(requestList.getAllRequest());
        tableView.setItems(dataRequestList);
        tableView.setOnMouseClicked(e->{
            event();
        });
    }
    private void setTableColumn(){
        categoryColumn.setCellValueFactory(new  PropertyValueFactory<>("category"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        voteColumn.setCellValueFactory(new PropertyValueFactory<>("votePoint"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    public void statusComboBoxSeleted(){
        tableView.getItems().clear();
        requestList = dataSource.searchStatus(dataSource.readfileRequest(), sortStatusComboBox.getValue());
        loadTable(requestList);
    }

    public void handleSortTimeSelected(){
        if (warningStatus.getText().equals("กรุณาเลือกเวลาหรือโหวตก่อน")){
            warningStatus.setText("");
        }
        tableView.getItems().clear();
        dataSource.sortTime(requestList,sortTimeComboBox.getValue());
        loadTable(requestList);
    }

    public void handleSortVoteSelected(){
        if (warningStatus.getText().equals("กรุณาเลือกเวลาหรือโหวตก่อน")){
            warningStatus.setText("");
        }
        tableView.getItems().clear();
        dataSource.sortVote(requestList,sortVoteComboBox.getValue());
        loadTable(requestList);
    }

    public void handleMoreThanVoteSearchButton(){
        if (sortVoteComboBox.getValue() == null && sortTimeComboBox.getValue() == null){
            warningStatus.setText("กรุณาเลือกเวลาหรือโหวตก่อน");
            warningStatus.setTextFill(Color.RED);
        }else{
            tableView.getItems().clear();
            loadTable(dataSource.searchMoreThan(requestList, Integer.parseInt(moreThanVoteTextField.getText())));
            moreThanVoteTextField.clear();
        }
    }
    public void handleUntilVoteSearchButton(){
        tableView.getItems().clear();
        loadTable(dataSource.searchUntil(requestList, Integer.parseInt(untilTextField.getText()), Integer.parseInt(toTextField.getText())));
        untilTextField.clear();
        toTextField.clear();

    }

    public void handleSortCategorySelected(){
        if (sortVoteComboBox.getValue() == null && sortTimeComboBox.getValue() == null){
            warningStatus.setText("กรุณาเลือกเวลาหรือโหวตก่อน");
            warningStatus.setTextFill(Color.RED);
        }else {
            warningStatus.setVisible(false);
            tableView.getItems().clear();
            requestList = dataSource.sortCategory(requestList, sortCategoryComboBox.getValue());
            loadTable(requestList);
        }
    }




}
