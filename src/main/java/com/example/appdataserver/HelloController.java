package com.example.appdataserver;

import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public  class HelloController implements Initializable{


    public TableView  <FileInfo> listServer;

    public void quitApp(ActionEvent actionEvent) {

    }
    public void  sendToUser(){


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Socket socket = new Socket("localhost",8899);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // @Override
   // public void initialize(URL url, ResourceBundle resourceBundle) {
   //     try {
   //         Socket  socket = new  Socket("localhost",8899);
   //     } catch (IOException e) {
   //         throw new RuntimeException(e);
   //     }
   // }
}
