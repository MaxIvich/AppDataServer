package com.example.appdataserver.Client;

public class FileMsg extends BasicResponse{

    public FileMsg(String response, String path, byte[] bytes) {
        super(response);
        Path = path;
        this.bytes = bytes;
    }

    public FileMsg(String path, byte[] bytes) {
        Path = path;
        this.bytes = bytes;
    }

    public String getPath() {
        return Path;
    }

    public byte[] getBytes() {
        return bytes;
    }

    private String Path;
    private byte[] bytes;
}
