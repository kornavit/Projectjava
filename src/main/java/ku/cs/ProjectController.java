package ku.cs;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import com.github.saacsos.FXRouter;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import ku.cs.models.modelRegister;
import javafx.fxml.FXML;
import ku.cs.services.StaffDataSource;


import java.io.IOException;

public class ProjectController {
    @FXML private PasswordField password;
    @FXML private TextField hiddenpassword;
    @FXML private TextField username;
    @FXML private CheckBox ShowPassword;
    @FXML private Label resultlogin;

    private modelRegister user;

    private StaffDataSource staffDataSource;
    public void handleNewRegisterButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("user_register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า nisit_register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public void handleLoginButton(ActionEvent actionEvent){
        user = new modelRegister(username.getText(),password.getText());
        if (user.role().equals("user")){
            try{
                FXRouter.goTo("user");
            }catch (IOException e){
                System.err.println("ไปที่หน้า nisit_register ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }else if (user.role().equals("admin")){
            try{
                FXRouter.goTo("admin");
            }catch (IOException e){
                System.err.println("ไปที่หน้า admin ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }else if (user.role().equals("staff")){
            try{
                staffDataSource = new StaffDataSource("data","user.csv");
                staffDataSource.StaffLogin(user);
                FXRouter.goTo("staff_main_menu", user);
            }catch(IOException e){
                System.err.println("ไปที่หน้า staff ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }else{
            resultlogin.setText(user.role());
        }
    }

    public void handleForgetPasswordButton(ActionEvent actionEvent) {
        try{
            FXRouter.goTo("user_change_password");
        } catch (IOException e){
            System.err.println("ไปที่หน้า user_change_password ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public void handleShowPassword(ActionEvent actionEvent){
        hiddenpassword.setVisible(false);
        if (ShowPassword.isSelected()) {
            hiddenpassword.setText(password.getText());
            hiddenpassword.setVisible(true);
            password.setVisible(false);
            return;
        }
        password.setText(hiddenpassword.getText());
        password.setVisible(true);
        hiddenpassword.clear();
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
