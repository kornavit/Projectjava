package ku.cs;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import com.github.saacsos.FXRouter;
import ku.cs.models.modelRegister;
import javafx.fxml.FXML;
import ku.cs.services.StaffDataSource;
import ku.cs.services.AdminDataSource;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectController {
    @FXML private PasswordField password;
    @FXML private TextField hiddenPassword;
    @FXML private TextField username;
    @FXML private CheckBox ShowPassword;
    @FXML private Label resultLogin;
    private StaffDataSource staffDataSource;
    private modelRegister user;

    public void handleNewRegisterButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("user_register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า nisit_register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public void handleLoginButton(ActionEvent actionEvent){

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        if (password.getText().equals("") || !password.getText().equals("")){ // ในกรณีที่กดตรง show password ก่อน
            if (hiddenPassword.getText().equals("")){
                password.setText(password.getText());
            }else{
                password.setText(hiddenPassword.getText());
            }
        } else {
            password.setText(password.getText());
        }
        System.out.println(password.getText());
        user = new modelRegister(username.getText(),password.getText());
        user.setTime(formattedDate);
        AdminDataSource adminDataSource = new AdminDataSource("data","login_time_user.csv");
        try {
            if (user.role().equals("user")) {
                if (user.getValue_ban().equals("true")){
                    adminDataSource.writeTimeLogin(user);
                    FXRouter.goTo("user",user);
                }else{
                    FXRouter.goTo("ban",user);
                }
            } else if (user.role().equals("admin")) {
                FXRouter.goTo("admin_main",user);
            } else if (user.role().equals("staff")) {
                adminDataSource.writeTimeLogin(user);
                staffDataSource = new StaffDataSource("data", "user.csv");
                staffDataSource.staffLogin(user);
                FXRouter.goTo("staff_main_menu", user);
            } else {
                resultLogin.setText(user.role());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleChangePasswordButton(ActionEvent actionEvent) {
        try{
            FXRouter.goTo("user_change_password");
        } catch (IOException e){
            System.err.println("ไปที่หน้า user_change_password ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public void handleShowPassword(ActionEvent actionEvent){
        hiddenPassword.setVisible(false);
        if (ShowPassword.isSelected()) {
            hiddenPassword.setText(password.getText());
            hiddenPassword.setVisible(true);
            password.setVisible(false);
            return;
        }
        password.setText(hiddenPassword.getText());
        password.setVisible(true);
        hiddenPassword.clear();
    }


    public void handleAboutButton(ActionEvent actionEvent) {
        try{
            FXRouter.goTo("about");
        }catch (IOException e){
            System.err.println("ไปหน้า about ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
