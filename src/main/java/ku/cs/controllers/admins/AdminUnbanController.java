package ku.cs.controllers.admins;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.models.modelBanUser;
import ku.cs.models.modelBanUserList;
import ku.cs.services.AdminDataSource;
import ku.cs.services.UserDataSource;

import java.io.IOException;

public class AdminUnbanController {
    @FXML
    private ListView<modelBanUser> tableListBan;
    @FXML
    private Label textDetail;
    @FXML
    private Label textUsername;
    @FXML
    private Button unbanButton;
    @FXML
    private Label username;
    @FXML
    private Label detailFromUser;
    private AdminDataSource adminDataSource;
    private modelBanUserList banUserList;
    private modelBanUser user;

    public void initialize(){
        unbanButton.setVisible(false);
        detailFromUser.setWrapText(true);
        textUsername.setText("");
        textDetail.setText("");
        adminDataSource = new AdminDataSource("data","ban.csv");
        banUserList = adminDataSource.readBanUser();
        showListview();
        clearSelectedList();
        handleSelectedListView();
    }
    private void handleSelectedListView(){
        tableListBan.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<modelBanUser>() {
            @Override
            public void changed(ObservableValue<? extends modelBanUser> observableValue, modelBanUser oldValue, modelBanUser newValue) {
                showSelectedUser(newValue);
            }
        });
    }
    private void showListview(){
        tableListBan.getItems().addAll(banUserList.getAllUsers());
        tableListBan.refresh();
    }

    private void showSelectedUser(modelBanUser banUser){
        textUsername.setText("ชื่อ :");
        textDetail.setText("คำร้องข้อคืนสิทธิ์ :");
        if (banUser != null){
            username.setText(banUser.getUsername());
            detailFromUser.setText(banUser.getDetailBan());
            user = banUser;
            unbanButton.setVisible(true);
        }
    }
    private void clearSelectedList(){
        unbanButton.setVisible(false);
        username.setText("");
        detailFromUser.setText("");
        textUsername.setText("");
        textDetail.setText("");
    }
    @FXML
    public void handleunbanUser(ActionEvent event) {
        UserDataSource userList = new UserDataSource("data","test_user_ban.csv");
        adminDataSource.unBanUser(adminDataSource.readBanUser(),userList.readData(),user);
        tableListBan.getItems().clear();
        clearSelectedList();
        banUserList = adminDataSource.readBanUser();
        showListview();
    }
    @FXML public void handleBackAdmin(){
        try{
            FXRouter.goTo("admin_main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
