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
        FXRouter.when("user_register",packageStr+"register/user_register.fxml");
        FXRouter.when("success",packageStr+"register/success.fxml");
        FXRouter.when("about",packageStr+"about.fxml");
        FXRouter.when("user_change_password",packageStr+"register/user_change_password.fxml",517,390);

        //testUser
        FXRouter.when("user", packageStr+"user/user.fxml");
        FXRouter.when("request", packageStr+"user/request.fxml",796,598);

        //request
        FXRouter.when("request_learning", packageStr+"user/requestLearningNext.fxml");
        FXRouter.when("request_building", packageStr+"user/requestBuildingNext.fxml");
        FXRouter.when("request_traffic", packageStr+"user/requestTrafficNext.fxml");
        FXRouter.when("request_finance", packageStr+"user/requestFinanceNext.fxml");
        FXRouter.when("request_other", packageStr+"user/requestOtherNext.fxml");

        //totalRequest & report
        FXRouter.when("total_complaint", packageStr+"user/totalComplaints.fxml");
        FXRouter.when("success_request", packageStr+"user/successRequest.fxml");
        FXRouter.when("report", packageStr+"user/report.fxml",845,647);
        FXRouter.when("success_report", packageStr+"user/successReport.fxml");
        FXRouter.when("request_detail", packageStr+"user/requestDetail.fxml",807.2,759.2);

        //profile
        FXRouter.when("profile_user", packageStr+"user/profileUser.fxml",1007.6,756.4);


        //staff
        FXRouter.when("staff_register",packageStr+"staff/staff_register.fxml",600,400);
        FXRouter.when("staff_main_menu",packageStr+"staff/staff_main_menu.fxml",900,600);

        //admin
        FXRouter.when("admin_main",packageStr + "admin/admin_main.fxml");
        FXRouter.when("ban_page",packageStr + "admin/ban.fxml");
        FXRouter.when("change_password_admin",packageStr + "admin/changepasswordAdmin.fxml",513,384);
        FXRouter.when("create_staff",packageStr + "admin/createStaff.fxml");
        FXRouter.when("unban",packageStr + "admin/unban.fxml",686,510);
    }

    public static void main(String[] args) {
        launch();
    }
}
