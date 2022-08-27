package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.github.saacsos.FXRouter;

import java.io.*;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public class NisitRegisterController {
    @FXML private TextField username;
    @FXML private PasswordField passwordReal; // รหัสผ่าน password field
    @FXML private TextField textRealpassword; // รหัสนิสิต text
    @FXML private PasswordField passwordAgain; // รหัสผ่าน password field
    @FXML private TextField textPasswordAgin; // ยืนยันรหัสผ่าน text
    @FXML private TextField name;
    @FXML private CheckBox showPassword;
    @FXML private Label resultCheckUsername; // บอกว่ามันใช่ชื่อนี้ได้ไหม

    @FXML private Label showerror;

    public String handleCheckUsernameButton(ActionEvent actionEvent) {
        String filePath = "data" + File.separator + "user.csv";
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line_name = "";
            while ( (line_name = buffer.readLine()) != null){
                if (line_name.equals(username.getText())){
                    resultCheckUsername.setTextFill(Color.RED);
                    resultCheckUsername.setText("มีชื่อนี้อยู่แล้ว");
                    try{
                        buffer.close();
                        reader.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return "กรุณาใส่ชื่อของคุณ";
                }
            }
            resultCheckUsername.setTextFill(Color.GREEN);
            resultCheckUsername.setText("ชื่อนี้ใช้ได้");
            return "ชื่อนี้ใช้ได้";
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void handleUploadNisitPhotoButton(ActionEvent actionEvent) {
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
        // check password for user
        String checkError = "";
        if (passwordReal.getText().equals("") || passwordAgain.getText().equals("")) {
            checkError += "กรุณาปลอก รหัสผ่าน หรือ รหัสผ่าน\n";
        } else if (! passwordReal.getText().equals(passwordAgain.getText())) {
            checkError += "กรุณาปลอก รหัสผ่านให้ตรงกัน\n";
        }

        // check username for user
        if (username.getText().equals("")){
            checkError += "กรุณาใส่ชื่อของคุณ\n";
        } else if (handleCheckUsernameButton(actionEvent).equals("กรุณาใส่ชื่อของคุณ")) {
            checkError += "ระบบมีชื่อนี้แล้ว กรุณาใส่ชื่อใหม่ของคุณ\n";
        }

        try {
            showerror.setTextFill(Color.RED);
            showerror.setText(checkError);
            showerror.setWrapText(true);
            if (checkError.equals("")){
                FXRouter.goTo("success");
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า success ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public void handleBackNisitRegisterButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("start");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า start ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
