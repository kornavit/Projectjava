package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.github.saacsos.FXRouter;
import java.io.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import ku.cs.models.modelRegister;
import ku.cs.services.ImageDataSource;
public class UserRegisterController {
    private String pickTarge;
    @FXML private TextField username;
    @FXML private PasswordField passwordReal; // รหัสผ่าน password field
    @FXML private TextField textRealpassword; // รหัสนิสิต text
    @FXML private PasswordField passwordAgain; // รหัสผ่าน password field
    @FXML private TextField textPasswordAgin; // ยืนยันรหัสผ่าน text
    @FXML private TextField name;
    @FXML private CheckBox showPassword;
    @FXML private Label resultCheckUsername; // บอกว่ามันใช่ชื่อนี้ได้ไหม
    @FXML private Label showerror;
    @FXML private ImageView nisitPhoto; // ไฟล์รูปภาพ

    private ImageDataSource getImage;

    @FXML public void initialize(){
        nisitPhoto.setImage(new Image(getClass().getResource("/ku/cs/images/default-profile.jpg").toExternalForm()));
    }

    public boolean handleCheckUsernameButton() {
        modelRegister user = new modelRegister(name.getText(),username.getText(),passwordReal.getText(),null,"user");
        if (user.checkusername()){
            resultCheckUsername.setTextFill(Color.GREEN);
            resultCheckUsername.setText("ชื่อนี้ใช้ได้");
            return true;
        }else{
            resultCheckUsername.setTextFill(Color.RED);
            resultCheckUsername.setText("มีชื่อนี้อยู่แล้ว");
            return false;
        }
    }

    public void handleUploadNisitPhotoButton(ActionEvent actionEvent) {
        getImage = new ImageDataSource();
        pickTarge = getImage.chooseImage("user_images");
        File destDir = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + pickTarge);
        nisitPhoto.setImage(new Image(destDir.toURI().toString()));
    }

    @FXML
    public void handlecheckpassword(ActionEvent actionEvent){
        if (showPassword.isSelected()){
            textPasswordAgin.setText(passwordAgain.getText());
            textRealpassword.setText(passwordReal.getText());
            textRealpassword.setVisible(true);
            textPasswordAgin.setVisible(true);
            passwordReal.setVisible(false);
            passwordAgain.setVisible(false);
            return;
        }
        passwordReal.setText(textRealpassword.getText());
        passwordAgain.setText(textPasswordAgin.getText());
        passwordReal.setVisible(true);
        passwordAgain.setVisible(true);
        textRealpassword.setVisible(false);
        textPasswordAgin.setVisible(false);
    }
    public void handleSubmitButton(ActionEvent actionEvent) {
        String checkError = "";
        // check username for user
        if (username.getText().equals("")) {
            checkError += "กรุณาใส่ชื่อของคุณ\n";
        } else if (!handleCheckUsernameButton()) {
            checkError += "ระบบมีชื่อนี้แล้ว กรุณาใส่ชื่อใหม่ของคุณ\n";
        }

        // check password for user
        if (passwordReal.getText().equals("") || passwordAgain.getText().equals("")) {
            checkError += "กรุณาปลอก รหัสผ่าน\n";
        } else if (!passwordReal.getText().equals(passwordAgain.getText())) {
            checkError += "กรุณาปลอก รหัสผ่านให้ตรงกัน\n";
        }

        // check name for user
        if (name.getText().equals("")) {
            checkError += "กรุณาใส่ชื่อในระบบของคุณ\n";
        }

        try {
            if (checkError.equals("")) {
                modelRegister user = new modelRegister(name.getText(), username.getText(), passwordReal.getText(), "user", pickTarge);
                user.add(user);
                FXRouter.goTo("success");
            } else {
                showerror.setTextFill(Color.RED);
                showerror.setText(checkError);
                showerror.setWrapText(true);
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า success ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleBackNisitRegisterButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("start");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า start ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
