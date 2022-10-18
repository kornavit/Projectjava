package ku.cs.services;

import javafx.scene.control.Alert;

public class AlertService {
    public void alertInformation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert!");
        alert.setHeaderText("Submit Successfully");
        alert.showAndWait();

    }

}
