module com.example.appdataserver {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.appdataserver to javafx.fxml;
    exports com.example.appdataserver;
}