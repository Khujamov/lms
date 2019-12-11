module com.restgo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.restgo.controller to javafx.fxml;
    exports com.restgo;
}