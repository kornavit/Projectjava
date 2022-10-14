package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import ku.cs.models.modelRequest;
import ku.cs.models.request.modelFinance;
import ku.cs.services.RequestListDataSource;

import java.io.IOException;

public class RequestFinanceNextController {
    @FXML private TextArea detail;
    @FXML private ChoiceBox<String> complaintFinance;
    private modelRequest request;

    String[] list = {"โอนเงิน","ฝากเงิน","ทุนการศึกษา","บริจาค","อื่น"};
    @FXML
    public void initialize(){
        //set list
        complaintFinance.getItems().addAll(list);
        complaintFinance.setValue("----------โปรดเลือก----------");

        request = (modelRequest) FXRouter.getData();
    }

    public void handleSubmitButton(ActionEvent actionEvent) {
        modelFinance finance = new modelFinance(complaintFinance.getValue(),detail.getText());
        request.setFinance(finance);

        RequestListDataSource dataSource = new RequestListDataSource("data/category");
        dataSource.writeFileRequest(request);

        try {
            FXRouter.goTo("success_request");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า success_request ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
