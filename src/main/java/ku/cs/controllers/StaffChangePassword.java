package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ku.cs.models.modelRegisterList;
import ku.cs.services.AlertService;
import ku.cs.services.UserChangePasswordDataSource;

import java.io.IOException;

public class StaffChangePassword {
    @FXML
    private TextField userName;
    @FXML
    private PasswordField oldPassword; // รหัสผ่าน password field

    @FXML
    private PasswordField newPassword; // รหัสผ่าน password field
    @FXML
    private Label showError1;
    @FXML
    private Label showError2;

    private UserChangePasswordDataSource dataSource;
    private modelRegisterList userList;
    private AlertService alertService;

    public boolean checkUsername() {
        dataSource = new UserChangePasswordDataSource("data", "user.csv");
        return dataSource.checkUsername(userName.getText());
    }

    public String checkPassword() {
        dataSource = new UserChangePasswordDataSource("data", "user.csv");
        return dataSource.checkPassword(userName.getText(), oldPassword.getText());
    }

    public void handleConfirmButton(ActionEvent actionEvent) {
        if (userName.getText().equals("")) {
            showError1.setTextFill(Color.RED);
            showError1.setText("ระบุชื่อสำหรับเข้าสู่ระบบ");
        } else if (checkUsername()) {
            showError1.setTextFill(Color.GREEN);
            showError1.setText("ชื่อสำหรับเข้าสู่ระบบถูกต้อง");
        } else {
            showError1.setTextFill(Color.RED);
            showError1.setText("ชื่อสำหรับเข้าสู่ระบบไม่ถูกต้อง");
        }

        if (checkPassword().equals("รหัสผ่านเดิมถูกต้อง")) {
            showError2.setTextFill(Color.GREEN);
            showError2.setText("รหัสผ่านเดิมถูกต้อง");
        } else {
            showError2.setTextFill(Color.RED);
            showError2.setText("รหัสผ่านเดิมไม่ถูกต้อง");
        }

        if (showError1.getText().equals("ชื่อสำหรับเข้าสู่ระบบถูกต้อง") && showError2.getText().equals("รหัสผ่านเดิมถูกต้อง")
                && !newPassword.getText().equals("")) {
            dataSource = new UserChangePasswordDataSource("data", "user.csv");
            userList = dataSource.readData();
            dataSource.writeData(userList, userName.getText(), newPassword.getText());
            alertService = new AlertService();
            alertService.alertInformation();
            //FXRouter.goTo("staff_main_menu");
        }
    }
    public void handleBackButton (ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staff_main_menu");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า staff main menu ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
}

