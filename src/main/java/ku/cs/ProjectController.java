package ku.cs;

import javafx.event.ActionEvent;

import java.io.IOException;

import com.github.saacsos.FXRouter;

public class ProjectController {
    public void handleNewRegisterButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("nisit_register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า nisit_register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleForgetPasswordButton(ActionEvent actionEvent) {
        //...
    }

    public void handleAboutButton(ActionEvent actionEvent) {
        try{
            FXRouter.goTo("about");
        }catch (IOException e){
            System.err.println("ไปหน้า about ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
