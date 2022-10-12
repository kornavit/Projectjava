package ku.cs.controllers.admins;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import ku.cs.models.modelRegister;
import ku.cs.services.ImageDataSource;
import ku.cs.services.UserDataSource;

import java.io.File;
import java.io.IOException;

public class CreateStaffController {
    @FXML private Label checkPassword;
    @FXML private Label checkUsername;
    @FXML private Label checkName;
    @FXML private Label checkCategory;
    @FXML private TextField usernameStaff;
    @FXML private TextField passwordReal;
    @FXML private TextField passwordAgain;
    @FXML private TextField nameStaff;
    @FXML private ChoiceBox<String> category;
    @FXML private ImageView photoStaff;
    private String pickTarget;
    private UserDataSource dataSource;
    private ImageDataSource imageDataSource;

    private String[] partCategory = {"building","learning","traffic","finance","other"};

    @FXML public void initialize(){
        dataSource = new UserDataSource("data","user.csv");
        category.setValue("-- หน่วยงาน --");
        category.getItems().addAll(partCategory);
        imageDataSource = new ImageDataSource();
        File destDir = new File("image" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + "default-profile.jpg");
        photoStaff.setImage(new Image(destDir.toURI().toString()));
        pickTarget = "default-profile.jpg";
    }
    @FXML
    void handleUploadPhotoStaff(ActionEvent event) {
        String picture = imageDataSource.chooseImage("user_images");
        if (picture.equals("")){
            return;
        }
        pickTarget = picture;
        File destDir = new File("image" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + pickTarget);
        photoStaff.setImage(new Image(destDir.toURI().toString()));
    }

    @FXML
    void handleCreateStaff(ActionEvent event) {
        checkUsername.setTextFill(Color.RED);
        checkPassword.setTextFill(Color.RED);
        checkName.setTextFill(Color.RED);
        checkCategory.setTextFill(Color.RED);
        if (usernameStaff.getText().equals("")){
            checkUsername.setText("กรุณาใส่ username");
            return ;
        } else if (!dataSource.readfile_user(usernameStaff.getText())){
            checkUsername.setText("มีชื่อนี้อยู่ในระบบแล้ว");
            return;
        }else {
            checkUsername.setTextFill(Color.GREEN);
            checkUsername.setText("สามารถใช้ชื่อนี้ได้");
        }
        if (passwordReal.getText().equals("") || passwordAgain.getText().equals("")){
            checkPassword.setText("กรุณาใส่ password");
            return ;
        } else if (!passwordReal.getText().equals(passwordAgain.getText())){
            checkPassword.setText("รหัสผ่านไม่เหมือนกัน");
            return ;
        }else {
            checkPassword.setTextFill(Color.GREEN);
            checkPassword.setText("รหัสผ่านเหมือนกันแล้ว");
        }
        if (nameStaff.getText().equals("")){
            checkName.setText("กรุณาระบุชื่อจริง");
            return ;
        }else {
            checkName.setText("");
        }
        if (category.getValue().equals("-- หน่วยงาน --")){
            checkCategory.setText("กรุณาระบุหน่วยงานด้วย");
            return ;
        }else {
            checkCategory.setText("");
        }
        modelRegister staff = new modelRegister(nameStaff.getText(),usernameStaff.getText(),passwordReal.getText(),"staff",pickTarget); // String name,String username,String password,String role,String image
        staff.setCategory(category.getValue());
        staff.setValue_ban("true");
        dataSource.writefile_user(staff);
        try {
            FXRouter.goTo("admin_main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleBackAdmin(ActionEvent event){
        try {
            FXRouter.goTo("admin_main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
