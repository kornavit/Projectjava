module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ku.cs to javafx.fxml;
    exports ku.cs;

    exports ku.cs.controllers;
    opens ku.cs.controllers to javafx.fxml;

    exports ku.cs.controllers.admins;
    opens ku.cs.controllers.admins to javafx.fxml;

    exports ku.cs.models;
    opens ku.cs.models to javafx.fxml;
}