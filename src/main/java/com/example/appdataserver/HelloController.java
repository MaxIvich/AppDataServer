package com.example.appdataserver;

import com.example.appdataserver.Client.BasicResponse;
import com.example.appdataserver.Client.Client;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public  class HelloController implements Initializable {



    public TableView  <FileInfo> listServer;
    public VBox userPanel;
    public HBox panelBox;
    public HBox buttonBox;
    public TextField loginField;
    public HBox authBox;
    public PasswordField passField;
    private Client client;



    public void quitApp(ActionEvent actionEvent) {
        Platform.exit();

    }
    public void  sendToServer(ActionEvent actionEvent){
        PaneController userPC = (PaneController) userPanel.getProperties().get("cntrl");
        if(userPC.getSelectedFileName()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Ни один вайл не был выбран",ButtonType.OK);
            alert.showAndWait();
            return;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    //  try {
    //      Socket socket = new Socket("localhost",8899);
    //  } catch (IOException e) {
    //      throw new RuntimeException(e);
    //  }
        client = new Client();


        TableColumn<FileInfo,String> fileTypeColumn = new TableColumn<>();
        fileTypeColumn.setCellValueFactory(param->new SimpleStringProperty(param.getValue().getFileType().getName()));
        fileTypeColumn.setPrefWidth(25);


        TableColumn<FileInfo,String>fileNameColumn = new TableColumn<>("Имя");
        fileNameColumn.setCellValueFactory(param->new SimpleStringProperty(param.getValue().getFilename()));
        fileNameColumn.setPrefWidth(200);

        TableColumn<FileInfo,Long>fileSizeColumn = new TableColumn<>("Размер");
        fileSizeColumn.setCellValueFactory(param->new SimpleObjectProperty<>(param.getValue().getFileSize()));
        fileSizeColumn.setPrefWidth(90);
        fileSizeColumn.setCellFactory(column ->{
            return new TableCell<FileInfo,Long>(){
                @Override
                protected void updateItem(Long item,boolean empty){
                    super.updateItem(item,empty);
                    if(item == null || empty){
                        setText("");
                        setStyle("");
                    }else {
                        String text = String.format("%,d bytes",item);

                        if(item == -1L){
                            text = "[Dir]";
                        }
                        setText(text);
                    }
                }

            };
        });
        listServer.getColumns().addAll(fileTypeColumn,fileNameColumn,fileSizeColumn);
        listServer.getSortOrder().add(fileTypeColumn);

        updateList(Path.of("SFiles"));

    }
    public void updateList(Path path){
        listServer.getItems().clear();
        try {
            listServer.getItems().addAll(Files.list(path).map(FileInfo::new).collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        listServer.sort();

    }


    public void copyToUser(ActionEvent actionEvent) {

    }

    public void delete(ActionEvent actionEvent) {

    }

    public void signInBtnClick(ActionEvent actionEvent) {
        String login  = loginField.getText();
        String pass = passField.getText();
        client.sendMsg( new BasicResponse(login + " " + pass));

    }

    public void RegBtnClick(ActionEvent actionEvent) {

    }
}
