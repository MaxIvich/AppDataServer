module com.example.appdataserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.netty.transport;
    requires io.netty.buffer;
    requires io.netty.codec;
    requires java.sql;


   opens com.example.appdataserver to javafx.fxml;
   exports com.example.appdataserver;

    exports com.example.appdataserver.Client;
    opens com.example.appdataserver.Client to javafx.fxml;

}