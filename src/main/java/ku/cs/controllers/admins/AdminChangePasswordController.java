package ku.cs.controllers.admins;

import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ku.cs.services.UserChangePasswordDataSource;

import java.io.IOException;

public class AdminChangePasswordController {
    @FXML
    private TextField usernameAdmin;
    @FXML
    private Label resultUsername;

    @FXML
    private TextField oldPasswordAdmin;
    @FXML
    private Label resultOldPassword;

    @FXML
    private TextField newPasswordAdmin;
    @FXML
    private Label resultNewPassword;

    private UserChangePasswordDataSource dataSource; // use the change register
    @FXML public void initialize(){
        dataSource = new UserChangePasswordDataSource("data","user.csv");
    }
    @FXML public void handleChangePasswordAdmin(){
        resultUsername.setTextFill(Color.RED);
        resultOldPassword.setTextFill(Color.RED);
        resultNewPassword.setTextFill(Color.RED);

        if (!dataSource.checkUsername(usernameAdmin.getText())){
            resultUsername.setText("ไม่มีชื่ออยู่ในระบบ");
            return;
        }else {
            resultUsername.setTextFill(Color.GREEN);
            resultUsername.setText("มีชื่อนี้อยู่ในระบบ");
        }

        if (oldPasswordAdmin.getText().equals("")){
            resultOldPassword.setText("ระบุรหัสผ่านเดิมด้วย");
            return;
        } else if (dataSource.checkPassword(usernameAdmin.getText(),oldPasswordAdmin.getText()).equals("รหัสผ่านเดิมไม่ถูกต้อง")) {
            resultOldPassword.setText("รหัสผ่านเดิมไม่ถูกต้อง");
            return;
        } else{
            resultOldPassword.setTextFill(Color.GREEN);
            resultOldPassword.setText("รหัสผ่านเดิมถูกต้อง");
        }

        if (newPasswordAdmin.getText().equals("")){
            resultNewPassword.setText("กรุณาใส่รหัสผ่านใหม่");
            return;
        }

        dataSource.writeData(dataSource.readData(),usernameAdmin.getText(),newPasswordAdmin.getText());
        try{
            FXRouter.goTo("admin_main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML public void handleBackAdmin(){
        try{
            FXRouter.goTo("admin_main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
