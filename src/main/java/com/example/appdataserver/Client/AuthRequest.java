package com.example.appdataserver.Client;

import com.example.appdataserver.Bd.BdUsers;

import java.sql.Connection;

public class AuthRequest implements BasicRequest{
    private String login;
    public String pass;
    BdUsers bdUsers;

    public AuthRequest(String login, String pass) {

        this.login = login;
        this.pass = pass;

    }
    @Override
    public String getType() {
        return "auth";
    }


}
