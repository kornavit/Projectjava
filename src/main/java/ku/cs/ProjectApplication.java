package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "white_nisit_shirt", 1024, 768);
        configRoute();
        FXRouter.goTo("start");
    }
    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("start", packageStr+"project.fxml");

        //login
        FXRouter.when("nisit_register",packageStr+"register/nisit_register.fxml");
        FXRouter.when("success",packageStr+"register/success.fxml");
        FXRouter.when("about",packageStr+"about.fxml");
        FXRouter.when("user_change_password",packageStr+"register/user_change_password.fxml",517,390);

        //testUser
        FXRouter.when("user", packageStr+"user/user.fxml");
        FXRouter.when("request", packageStr+"user/request.fxml");
        FXRouter.when("request_next", packageStr+"user/requestNext.fxml");
        FXRouter.when("total_complaint", packageStr+"user/totalComplaints.fxml");
        FXRouter.when("success_request", packageStr+"user/successRequest.fxml");

        //staff
        FXRouter.when("staff_register",packageStr+"staff/staff_register.fxml",600,400);
        FXRouter.when("staff_main_menu",packageStr+"staff/staff_main_menu.fxml",900,600);



    }

    public static void main(String[] args) {
        launch();
    }
}
