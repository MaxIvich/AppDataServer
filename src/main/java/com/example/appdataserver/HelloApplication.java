package com.example.appdataserver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;

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
        authorise(stage);
        controller = fxmlLoader.getController();
        if (controller.isIsAuth()){
            stage.show();
        }
        stage.setOnCloseRequest((event) -> controller.close());
    }
    public void authorise(Stage parent) throws IOException {
        Stage authStage = new Stage();
        FXMLLoader loader = new FXMLLoader(AuthorisationController.class.getResource("authorisation.fxml"));
        Scene authScene = new Scene(loader.load(), 900,480);
        authStage.setTitle("Авторизация");
        authStage.setScene(authScene);
        AuthorisationController authController = loader.getController();
        authController.setController(controller);
        authStage.showAndWait();


    }


    public static void main(String[] args) {
        launch();
    }
}