module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;


    opens ku.cs to javafx.fxml;
    exports ku.cs;

    exports ku.cs.Controller;
    opens ku.cs.Controller to javafx.fxml;
}