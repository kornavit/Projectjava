package ku.cs.controllers.admins;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import ku.cs.models.modelBanUser;
import ku.cs.models.modelRequest;

import java.io.IOException;

public class AdminBanController {
    @FXML
    private ListView<modelRequest> tableRequest;
    @FXML
    private ListView<modelRequest> tableUserBan;

    @FXML private Pane paneDeleteDetail;
    @FXML private Label head;
    @FXML private Label usernameReport;
    @FXML private Label detail;
    @FXML private Label detailReport;

    @FXML private Pane paneBan;
    @FXML private Label usernameBan;
    @FXML private Label detailBan;

    @FXML public void initialize(){
        paneBan.setVisible(false);
        paneDeleteDetail.setVisible(false);
    }
    @FXML
    void handleBanUser(ActionEvent event) {

    }

    @FXML
    void handledeleteDetail(ActionEvent event) {

    }


    @FXML public void handlegotounban(ActionEvent event){
        try{
            FXRouter.goTo("unban");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void handleBackAdmin(ActionEvent event){
        try{
            FXRouter.goTo("admin_main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
