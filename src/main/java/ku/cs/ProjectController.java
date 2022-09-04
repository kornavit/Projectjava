package ku.cs;

import javafx.event.ActionEvent;

import java.io.IOException;

import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.modelregister;

public class ProjectController {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label resultlogin;
    public void handleNewRegisterButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("nisit_register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า nisit_register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public void handleloginButton(ActionEvent actionEvent){
        System.out.println(username.getText() + ',' + password.getText());
        modelregister user = new modelregister(username.getText(),password.getText());
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
                System.err.println("ไปที่หน้า nisit_register ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }else if (user.role().equals("officer")){
            try{
                FXRouter.goTo("staff_main_menu");
            }catch(IOException e){
                System.err.println("ไปที่หน้า nisit_register ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }else{
            resultlogin.setText(user.role());
        }
    }

    public void handleForgetPasswordButton(ActionEvent actionEvent) {
        //...
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
