package ku.cs.controllers.admins;

import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.modelRegister;
import ku.cs.models.modelRegisterList;
import ku.cs.services.AdminDataSource;
import ku.cs.services.ImageDataSource;
import ku.cs.services.UserDataSource;

import java.io.File;
import java.io.IOException;

public class AdminMainController {
    @FXML private TableView<modelRegister> userBoard;
    @FXML private TableColumn<modelRegister, String> name;
    @FXML private TableColumn<modelRegister, String> username;
    @FXML private TableColumn<modelRegister, String> category;
    @FXML private TableColumn<modelRegister, String> time;
    // admin pane
    @FXML private Label nameAdmin;
    @FXML private ImageView imageAdmin;
    //text
    @FXML private Label textName;
    @FXML private Label textUsername;
    @FXML private Label textCategory;
    @FXML private Label textTime;

    //selected user
    @FXML private ImageView imageUser;
    @FXML private Label userName;
    @FXML private Label userUsername;
    @FXML private Label userCategory;
    @FXML private Label timeLogin;
    private AdminDataSource dataSource;
    private ObservableList<modelRegister> loginBoard;
    private modelRegisterList userList;
    private modelRegister admin;
    private ImageDataSource imageDataSource;
    private UserDataSource userDataSource;
    public void initialize(){
        admin = (modelRegister) FXRouter.getData();
        imageDataSource = new ImageDataSource();
        userDataSource = new UserDataSource("data","user.csv");
        nameAdmin.setText(admin.getName());
        File image_fact = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + admin.getImagePath());
        imageAdmin.setImage(new Image(image_fact.toURI().toString()));

        dataSource = new AdminDataSource("data","login_time_user.csv");
        userList = dataSource.readData();
        loginBoard = FXCollections.observableArrayList();
        setMenuTable();
        loadTable();
        event();
    }
    private void setMenuTable(){
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
    private void loadTable(){
        loginBoard.addAll(userList.getAllUsers());
        userBoard.setItems(loginBoard);
        userBoard.setOnMouseClicked(e ->{
            event();
        });
    }
    private void event(){
        modelRegister user = userBoard.getSelectionModel().getSelectedItem();
        if (user != null){ // มันอาจจะเป็น null
            textName.setText("name : ");
            textUsername.setText("username : ");
            textCategory.setText("category : ");
            textTime.setText("time : ");

            userName.setText(user.getName());
            userUsername.setText(user.getUsername());
            userCategory.setText(user.getCategory());
            timeLogin.setText(user.getTime());
            File destdir_user = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + dataSource.pickImageUser(user));
            imageUser.setImage(new Image(destdir_user.toURI().toString()));
        }
    }

    public void handleChangeImage(){
        String pickTarge = imageDataSource.chooseImage("user_images");
        if (pickTarge.equals("")){
            return;
        }
        File destDir = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + pickTarge);
        imageAdmin.setImage(new Image(destDir.toURI().toString()));
        admin.setImagePath(pickTarge);
        userDataSource.writeImage(userDataSource.readData(),admin);
    }
    public void handleCreateStaffpage(){
        try{
            FXRouter.goTo("create_staff");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void handleBanlist(){
        try{
            FXRouter.goTo("ban_page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void handleGotoChangePasswordAdmin(){
        try{
            FXRouter.goTo("change_password_admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void handleGotoLogin(){
        try{
            FXRouter.goTo("start");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
