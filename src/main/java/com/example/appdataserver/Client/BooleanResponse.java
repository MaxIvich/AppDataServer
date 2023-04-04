package com.example.appdataserver.Client;

import java.io.Serializable;

public class BooleanResponse implements Serializable {

    private boolean response;
    public BooleanResponse(boolean response){
        this.response = response;

    }

    public boolean isResponse() {
        return response;
    }
}
