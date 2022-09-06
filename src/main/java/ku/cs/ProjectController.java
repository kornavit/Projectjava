package ku.cs;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;


import java.io.IOException;


public class ProjectController {
    @FXML private PasswordField userHiddenPassword;
    @FXML private TextField userPassword;
    @FXML private CheckBox ShowPassword;
    public void handleNewRegisterButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("nisit_register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า nisit_register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleForgetPasswordButton(ActionEvent actionEvent) {
        try{
            com.github.saacsos.FXRouter.goTo("user_change_password");
        } catch (IOException e){
            System.err.println("ไปที่หน้า user_change_password ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    //25/8/2022 edit
    public void handleShowPassword(ActionEvent actionEvent){
        userPassword.setVisible(false);
        if (ShowPassword.isSelected()) {
            userPassword.setText(userHiddenPassword.getText());
            userPassword.setVisible(true);
            userHiddenPassword.setVisible(false);
            return;
        }
        userHiddenPassword.setText(userPassword.getText());
        userHiddenPassword.setVisible(true);
        userPassword.clear();
    }


    public void handleAboutButton(ActionEvent actionEvent) {
        try{
            com.github.saacsos.FXRouter.goTo("about");
        }catch (IOException e){
            System.err.println("ไปหน้า about ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleLoginButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("user");
        }catch (IOException e){
            System.err.println("ไปหน้า user ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
