package com.example.appdataserver.Client;

import java.io.Serializable;

public class BasicResponse  implements Serializable {
    private String response;

    public BasicResponse(String response) {
        this.response = response;
    }

    public BasicResponse() {
    }

    public String getResponse() {
        return response;
    }


}
