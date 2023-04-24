package com.example.appdataserver.Client;

import java.io.Serializable;

public class RegResponse extends BasicResponse{
    private String response;

    public RegResponse(String response){

        this.response = response;
    }
    public String getResponse() {
        return response;
    }
}
