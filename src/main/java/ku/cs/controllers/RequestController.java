package ku.cs.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.fxml.Initializable;

import com.github.saacsos.FXRouter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestController implements Initializable{

    @FXML private ComboBox<String> complaintCategory;
    @FXML private Label test1;
    @FXML private ComboBox<String> complaintStatus;
    @FXML private TextField headTextField;
    @FXML private TextArea bodyTextArea;

//    @FXML public void initialize(){
//    }
    ObservableList<String> list = FXCollections.observableArrayList("การเรียนการสอน", "อาคาร สถานที่และสิ่งอำนวยความสะดวก", "การจราจรในมหาวิทยาลัย", "อื่นๆ");
    ObservableList<String> status = FXCollections.observableArrayList("นิสิต(Student)", "บุคลากร(Staff)", "ศิษย์เก่า(Alumni)");
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle){
        complaintCategory.setItems(list);
        complaintStatus.setItems(status);
    }
    public void handleBackUserButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("user");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า user ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public int changCategory(ActionEvent actionEvent) {
        String test3 = complaintCategory.getSelectionModel().getSelectedItem();
        test1.setText(test3);
        int select = 0;
        if (test3 != null) {
            if (test3.compareTo(list.get(0)) == 0) {
                select = 1;
            } else if (test3.compareTo(list.get(1)) == 0) {
                select = 2;
            } else if (test3.compareTo(list.get(2)) == 0) {
                select = 3;
            } else if (test3.compareTo(list.get(3)) == 0) {
                select = 4;
            }
        }
        return select;
    }

    public void handleNextButton(ActionEvent actionEvent) {
        try {
            if (changCategory(actionEvent) == 1)
                FXRouter.goTo("request_learning");
            else if (changCategory(actionEvent) == 2)
                FXRouter.goTo("request_building");
            else if (changCategory(actionEvent) == 3)
                FXRouter.goTo("request_traffic");
            else  if (changCategory(actionEvent) == 4)
                FXRouter.goTo("request_other");


        } catch (IOException e) {
            System.err.println("ไปที่หน้า request_next ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }





}
