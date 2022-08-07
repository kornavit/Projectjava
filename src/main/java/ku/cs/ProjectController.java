package ku.cs;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ProjectController {
    public void handleNewRegisterButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("nisit_register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า nisit_register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleForgetPasswordButton(ActionEvent actionEvent) {
        //...
    }
}
