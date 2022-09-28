package ku.cs;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import com.github.saacsos.FXRouter;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import ku.cs.models.modelRegister;
import javafx.fxml.FXML;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectController {
    @FXML private PasswordField password;
    @FXML private TextField hiddenpassword;
    @FXML private TextField username;
    @FXML private CheckBox ShowPassword;
    @FXML private Label resultlogin;
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
        System.out.println("time: " + formattedDate);

        modelRegister user = new modelRegister(username.getText(),password.getText());

        try {
            if (user.role().equals("user")) {
                FXRouter.goTo("user");
//                if (user.getValue_ban().equals("true")){
//
//                    FXRouter.goTo("user");
//                }//else{
//                    FXRouter.goTo("");
//                }
            } else if (user.role().equals("admin")) {
                FXRouter.goTo("admin_main");
            } else if (user.role().equals("staff")) {
                FXRouter.goTo("staff_register");
            } else {
                resultlogin.setText(user.role());
            }
        }catch (IOException e){
                System.err.println("ไปที่หน้าที่กำลัง login ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
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
    //25/8/2022 edit
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
