package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ku.cs.models.modelRegister;
import ku.cs.services.AdminDataSource;

import java.io.IOException;
import java.util.Date;

public class BanUserController {
    @FXML
    private Label checkUsername;

    @FXML
    private TextArea userDetail;

    @FXML
    private TextField userUsername;

    private AdminDataSource adminDataSource;
    private modelRegister user;

    public void initialize(){ // username,login,detail
        user = (modelRegister) FXRouter.getData();
        adminDataSource = new AdminDataSource("data","ban.csv");
        adminDataSource.countLoginBan(adminDataSource.readBanUser("data","ban.csv"),user);
    }
    @FXML
    void handleSubmitToAdmin(ActionEvent event){
        if (userUsername.getText().equals("")){
            checkUsername.setTextFill(Color.BLACK);
            checkUsername.setText("กรุณาใส่ชื่อ");
            return;
        }
        adminDataSource = new AdminDataSource("data","ban.csv");
        if (adminDataSource.checkuserban(userUsername.getText())){
            checkUsername.setTextFill(Color.GREEN);
            checkUsername.setText("มีชื่อนี้อยู่ในการแบน");
            if (!userDetail.getText().equals("")){
                try {
                    adminDataSource.writeBanDetailUser(adminDataSource.readBanUser("data","ban.csv"),userDetail.getText(),userUsername.getText());
                    FXRouter.goTo("start");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else {
            checkUsername.setTextFill(Color.RED);
            checkUsername.setText("ไม่มีชื่อนี้อยู่ในการแบน");
        }
    }

    @FXML
    void handleBacktologin(ActionEvent event) {
        try {
            FXRouter.goTo("start");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
