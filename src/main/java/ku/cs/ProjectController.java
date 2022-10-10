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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("time: " + formattedDate);

        //System.out.println(password.getText());
        modelRegister user = new modelRegister(username.getText(),password.getText());
        try {
            if (user.search_role().equals("user")) {
                FXRouter.goTo("user",user);
//                if (user.getValue_ban().equals("true")){
//                    FXRouter.goTo("user");
//                }//else{
//                    FXRouter.goTo("");
//                }
            } else if (user.search_role().equals("admin")) {
                FXRouter.goTo("admin_main");
            } else if (user.search_role().equals("staff")) {
                staffDataSource = new StaffDataSource("data", "user.csv");
                staffDataSource.StaffLogin(user);
                FXRouter.goTo("staff_main_menu", user);
            }else {
                resultlogin.setText(user.search_role());
            }
        }
        catch (IOException e){
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
