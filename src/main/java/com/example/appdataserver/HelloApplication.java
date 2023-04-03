package com.example.appdataserver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
       String URL = "hello-view.fxml";
       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(URL));
       System.out.println(fxmlLoader.getLocation());
       Scene scene = new Scene(fxmlLoader.load(), 900, 480);
        stage.setTitle("App Data Server");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}