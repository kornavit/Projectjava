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
import ku.cs.services.ImageDataSource;
import ku.cs.services.UserDataSource;

import java.io.File;
import java.io.IOException;

public class AdminMainController {
    @FXML private TableView<modelRegister> user_board;
    @FXML private TableColumn<modelRegister, String> name;
    @FXML private TableColumn<modelRegister, String> username;
    @FXML private TableColumn<modelRegister, String> category;
    @FXML private TableColumn<modelRegister, String> time;
    // admin pane
    @FXML private Label name_admin;
    @FXML private ImageView image_admin;
    //text
    @FXML private Label text_name;
    @FXML private Label text_username;
    @FXML private Label text_category;
    @FXML private Label text_time;

    //selected user
    @FXML private ImageView image_user;
    @FXML private Label user_name;
    @FXML private Label user_username;
    @FXML private Label user_category;
    @FXML private Label time_login;
    private UserDataSource dataSource;
    private ObservableList<modelRegister> login_board;
    private modelRegisterList user_list;

    public void initialize(){
        name_admin.setText("boss");
        File image_fact = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + "boss.jpg");
        image_admin.setImage(new Image(image_fact.toURI().toString()));

        dataSource = new UserDataSource("data","login_time_user.csv");
        //user_list = dataSource.read_admin();
        login_board = FXCollections.observableArrayList();
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
        login_board.addAll(user_list.getAllUsers());
        user_board.setItems(login_board);
        user_board.setOnMouseClicked(e ->{
            event();
        });
    }
    private void event(){
        modelRegister user = user_board.getSelectionModel().getSelectedItem();
        if (user != null){ // มันอาจจะเป็น null
            text_name.setText("name : ");
            text_username.setText("username : ");
            text_category.setText("category : ");
            text_time.setText("time : ");

            user_name.setText(user.getName());
            user_username.setText(user.getUsername());
            user_category.setText(user.getCategory());
            time_login.setText(user.getTime());
            File destdir_user = new File("image_user" + System.getProperty("file.separator") + "user_images" + System.getProperty("file.separator") + user.getImagePath());
            image_user.setImage(new Image(destdir_user.toURI().toString()));
        }
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
