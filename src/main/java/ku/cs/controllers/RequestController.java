package ku.cs.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

import com.github.saacsos.FXRouter;
import javafx.scene.paint.Color;
import ku.cs.models.modelRequest;
import ku.cs.models.modelUser;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class RequestController implements Initializable{

    private modelUser name;
    @FXML private ComboBox<String> complaintCategory;
    @FXML private TextField headTextField;
    @FXML private Label showerror;

    ObservableList<String> list = FXCollections.observableArrayList("การเรียนการสอน", "อาคาร สถานที่และสิ่งอำนวยความสะดวก", "การจราจรในมหาวิทยาลัย","การเงินในมหาวิทยาลัย", "อื่นๆ");
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle){
        name = (modelUser) FXRouter.getData();
        complaintCategory.setItems(list);
    }
    public void handleBackUserButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("user");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า user ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public int changeCategory(ActionEvent actionEvent) {
        String testCategory = complaintCategory.getSelectionModel().getSelectedItem();
        int select = 0;
        if (testCategory != null) {
            if (testCategory.compareTo(list.get(0)) == 0) {
                select = 1;
            } else if (testCategory.compareTo(list.get(1)) == 0) {
                select = 2;
            } else if (testCategory.compareTo(list.get(2)) == 0) {
                select = 3;
            } else if (testCategory.compareTo(list.get(3)) == 0) {
                select = 4;
            }else if (testCategory.compareTo(list.get(4)) == 0){
                select = 5;
            }
        }
        return select;
    }

    public void handleNextButton(ActionEvent actionEvent) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        modelRequest request = new modelRequest(name.getName(), headTextField.getText(),formattedDate,"ยังไม่ดำเนินการ",0 );
        try {
            String checkError = "";
            if (changeCategory(actionEvent) == 1 && !headTextField.getText().equals("")) {
                request.setCategory("learning");
                FXRouter.goTo("request_learning", request);
            }
            else if (changeCategory(actionEvent) == 2 && !headTextField.getText().equals("")){
                request.setCategory("building");
                FXRouter.goTo("request_building", request);
            }
            else if (changeCategory(actionEvent) == 3 && !headTextField.getText().equals("")) {
                request.setCategory("traffic");
                FXRouter.goTo("request_traffic", request);
            }
            else if (changeCategory(actionEvent) == 4 && !headTextField.getText().equals("")){
                request.setCategory("finance");
                FXRouter.goTo("request_finance", request);
            }
            else  if (changeCategory(actionEvent) == 5 && !headTextField.getText().equals("")) {
                FXRouter.goTo("request_other", request);
                request.setCategory("other");
            }
            else {
                checkError += "กรุณากรอกหัวเรื่อง หรือเลือกหมวดหมู่เรื่องร้องเรียน\n";
                showerror.setTextFill(Color.RED);
                showerror.setText(checkError);
                showerror.setWrapText(true);
                System.err.println("ยังไม่ได้กรอกหัวเรื่อง หรือเลือกหมวดหมู่");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
//            System.err.println("ไปที่หน้า request_next ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
