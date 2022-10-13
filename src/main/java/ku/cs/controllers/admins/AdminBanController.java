package ku.cs.controllers.admins;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import ku.cs.models.modelBanUser;
import ku.cs.models.modelBanUserList;
import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;
import ku.cs.services.AdminDataSource;
import ku.cs.services.RequestListDataSource;
import ku.cs.services.UserDataSource;

import java.io.IOException;

public class AdminBanController {
    @FXML
    private ListView<modelRequest> tableRequest;
    @FXML
    private ListView<modelBanUser> tableUserBan;

    @FXML private Pane paneDeleteDetail;
    @FXML private Label head;
    @FXML private Label usernameReport;
    @FXML private Label detail;
    @FXML private Label detailReport;

    @FXML private Pane paneBan;
    @FXML private Label usernameBan;
    @FXML private Label detailBan;

    private AdminDataSource adminDataSource;
    private modelBanUserList banUserList;
    private modelRequestList requestList;
    private modelBanUser banUser;
    private modelRequest deleteRequest;

    @FXML public void initialize(){
        paneBan.setVisible(false);
        paneDeleteDetail.setVisible(false);
        detail.setWrapText(true);
        detailReport.setWrapText(true);
        detailBan.setWrapText(true);
        adminDataSource = new AdminDataSource("data","deleteDetail.csv");
        // show List Request
        showListviewRequest();
        clearSelectedListRequest();
        handleSelectedListViewRequest();
        // show List Ban
        showListviewBan();
        clearSelectedListBan();
        handleSelectedListViewBan();
    }
    private void handleSelectedListViewRequest(){
        tableRequest.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<modelRequest>() {
            @Override
            public void changed(ObservableValue<? extends modelRequest> observableValue, modelRequest oldRequest, modelRequest newRequest) {
                showSelectedRequest(newRequest);
            }
        });
    }
    private void showListviewRequest(){
        requestList = adminDataSource.readRequestReport();
        tableRequest.getItems().addAll(requestList.getAllRequest());
        tableRequest.refresh();
    }
    private void showSelectedRequest(modelRequest request){
        if (request != null){
            paneDeleteDetail.setVisible(true);
            head.setText(request.getSubject());
            usernameReport.setText(request.getUserName());
            adminDataSource.readRequestDetailReport(request);
            detail.setText(request.getRequestDetail().replace("|","\n"));
            detailReport.setText(request.getReportDetail().replace("|","\n"));
            deleteRequest = request;
        }
    }
    private void clearSelectedListRequest(){
        paneDeleteDetail.setVisible(false);
    }
    @FXML
    public void handledeleteDetail(ActionEvent event) {
        RequestListDataSource requestDataSource = new RequestListDataSource();
        adminDataSource.deleteRequest(requestDataSource.readAdminRequest(deleteRequest.getCategory()),adminDataSource.readRequestReport(),deleteRequest);
        tableRequest.getItems().clear();
        clearSelectedListRequest();
        showListviewRequest();
    }
    private void handleSelectedListViewBan(){
        tableUserBan.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<modelBanUser>() {
            @Override
            public void changed(ObservableValue<? extends modelBanUser> observableValue, modelBanUser oldBan, modelBanUser newBan) {
                showSelectedBan(newBan);
            }
        });
    }
    private void showListviewBan(){
        banUserList = adminDataSource.readBanUser("data","preBan.csv");
        tableUserBan.getItems().addAll(banUserList.getAllUsers());
        tableUserBan.refresh();
    }
    private void showSelectedBan(modelBanUser user){
        if (user != null){
            paneBan.setVisible(true);
            usernameBan.setText(user.getUsername());
            detailBan.setText(user.getDetailBan().replace("|","\n"));
            banUser = user;
        }
    }
    private void clearSelectedListBan(){
        paneBan.setVisible(false);
    }
    @FXML
    public void handleBanUser(ActionEvent event) {
        UserDataSource dataSource = new UserDataSource("data","user.csv");
        adminDataSource.banUser(adminDataSource.readBanUser("data","preBan.csv"),dataSource.readData(),banUser);
        tableUserBan.getItems().clear();
        clearSelectedListBan();
        showListviewBan();
    }


    @FXML public void handlegotounban(ActionEvent event){
        try{
            FXRouter.goTo("unban");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void handleBackAdmin(ActionEvent event){
        try{
            FXRouter.goTo("admin_main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
