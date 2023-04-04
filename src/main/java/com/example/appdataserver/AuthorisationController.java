package com.example.appdataserver;

import com.example.appdataserver.Client.BasicResponse;
import com.example.appdataserver.Client.Client;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AuthorisationController{
    public HBox authBox;
    public TextField loginField;
    public PasswordField passField;
    private HelloController controller;

    private String login;
    private String pass;


    public void signInBtnClick(ActionEvent actionEvent) {

        String login  = loginField.getText();
        String pass = passField.getText();
        controller.sendMsg( new BasicResponse(login + " " + pass));
    }
    public void RegBtnClick(ActionEvent actionEvent) {
    }
    public void setController(HelloController controller){
        this.controller = controller;
    }
}

