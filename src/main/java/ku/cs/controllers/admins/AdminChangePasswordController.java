package ku.cs.controllers.admins;

import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;

import java.io.IOException;

public class AdminChangePasswordController {
    @FXML public void handleChangePasswordAdmin(){

    }
    @FXML public void handleBackAdmin(){
        try{
            FXRouter.goTo("admin_main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
