package com.example.appdataserver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private HelloController controller;

    @Override
    public void start(Stage stage) throws IOException {
       String URL = "hello-view.fxml";
       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(URL));
       System.out.println(fxmlLoader.getLocation());
       Scene scene = new Scene(fxmlLoader.load(), 900, 480);
        stage.setTitle("App Data Server");
        stage.setScene(scene);

        controller = fxmlLoader.getController();


        stage.show();
        stage.setOnCloseRequest((event) -> controller.close());
    }



    public static void main(String[] args) {
        launch();
    }
}