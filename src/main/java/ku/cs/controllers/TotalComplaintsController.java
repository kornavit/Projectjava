package ku.cs.controllers;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.modelAbout;
import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;
import ku.cs.services.modelRequestHardCode;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class TotalComplaintsController{

//    @FXML private ListView<modelTotalComplaints> complaintsList;
    @FXML private TableView tableView;
    @FXML private TableColumn subjectList;
    @FXML private TableColumn categoryList;
    @FXML private TableColumn scoreList;
    @FXML private TableColumn statusList;
    @FXML private TableColumn timeList;

    @FXML private Label categoryLabel;
    @FXML private Label nameLabel;
    @FXML private Label headLabel;
    @FXML private Label detailLabel;

    private modelRequestHardCode dataSource;

    private modelRequestList requestList;


    @FXML public void initialize(){
        categoryList.setCellValueFactory(new  PropertyValueFactory<modelRequest, String>("category"));
        subjectList.setCellValueFactory(new PropertyValueFactory<modelRequest, String>("subject"));
        //scoreColumn.setCellValueFactory(new PropertyValueFactory<modelRequest, String>("voteScore"));
        statusList.setCellValueFactory(new PropertyValueFactory<modelRequest, String>("status"));
        //timeColumn.setCellValueFactory(new PropertyValueFactory<modelRequest, String>("time"));

        dataSource = new modelRequestHardCode();
        requestList = dataSource.getRequestList();
        ObservableList<modelRequest> requestList = tableView.getItems();
        tableView.setItems(requestList);
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
        tableView.getItems().addAll(requestList.getAllRequest());
        tableView.refresh();
    }

}
