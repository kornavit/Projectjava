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
import java.util.ResourceBundle;

public class RequestController implements Initializable{

    private modelUser name;
    @FXML private ComboBox<String> complaintCategory;
    @FXML private TextField headTextField;
    @FXML private Label test;
    @FXML private Label showerror;

    ObservableList<String> list = FXCollections.observableArrayList("การเรียนการสอน", "อาคาร สถานที่และสิ่งอำนวยความสะดวก", "การจราจรในมหาวิทยาลัย", "อื่นๆ");
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle){
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
        test.setText(testCategory);
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
            }
        }
        return select;
    }

//    public void handleNextButton(ActionEvent actionEvent) {
//        name = (modelUser) FXRouter.getData();
//        modelRequest request = new modelRequest(name.getName(), test.getText(), headTextField.getText(),"ยังไม่ดำเนินการ" );
//        try {
//            String checkError = "";
//            if (changeCategory(actionEvent) == 1 && !headTextField.getText().equals("")) {
//                FXRouter.goTo("request_learning", request);
//                request.addRequestLearning(request);
//            } else if (changeCategory(actionEvent) == 2 && !headTextField.getText().equals("")) {
//                FXRouter.goTo("request_building", request);
//                request.addRequestBuilding(request);
//            } else if (changeCategory(actionEvent) == 3 && !headTextField.getText().equals("")){
//                FXRouter.goTo("request_traffic", request);
//                request.addRequestTraffic(request);
//            } else if (changeCategory(actionEvent) == 4 && !headTextField.getText().equals("")){
//                FXRouter.goTo("request_other", request);
//                request.addRequestOther(request);
//            }else {
//                checkError += "กรุณากรอกหัวเรื่อง หรือเลือกหมวดหมู่เรื่องร้องเรียน\n";
//                showerror.setTextFill(Color.RED);
//                showerror.setText(checkError);
//                showerror.setWrapText(true);
//            }
//
//
//        } catch (IOException e) {
//            System.err.println("ไปที่หน้า request_next ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกำหนด route");
//        }
}
