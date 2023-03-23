module com.example.appdataserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.netty.transport;
    requires io.netty.buffer;


    opens com.example.appdataserver to javafx.fxml;
    exports com.example.appdataserver;
}