package ku.cs.controllers;

import javafx.event.ActionEvent;

import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StaffRegisterController
{
    @FXML private TextField staffUsername;
    @FXML private PasswordField hiddenPassword;
    @FXML private TextField staffPassword;
    @FXML private CheckBox showPassword;

    @FXML private PasswordField confirmPassword;

    @FXML private ImageView registerImageView;

    @FXML private ImageView logo;

    @FXML
    public void initialize() {
        String url = getClass().getResource("/ku/cs/images/KU_Logo_PNG.png").toExternalForm();
        logo.setImage(new Image(url));
    }

    @FXML
    public void handleCheckUsername(){
    }

    @FXML
    public void handleShowPassword(ActionEvent actionEvent){
        staffPassword.setVisible(false);
        if (showPassword.isSelected()) {
            staffPassword.setText(hiddenPassword.getText());
            staffPassword.setVisible(true);
            hiddenPassword.setVisible(false);
            return;
        }
        hiddenPassword.setText(staffPassword.getText());
        hiddenPassword.setVisible(true);
        staffPassword.clear();

    }

    @FXML
    public void ConfirmBtn(){
    }

    @FXML
    public void StaffProfilePic(){
    }

    @FXML
    public void ExitBtn(){

    }

}
