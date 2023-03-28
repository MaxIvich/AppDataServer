package com.example.appdataserver.Client;

public class AuthRequest implements BasicRequest{
    private String login;
    public String pass;


    public AuthRequest(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }


    @Override
    public String getType() {
        return "auth";
    }
}
