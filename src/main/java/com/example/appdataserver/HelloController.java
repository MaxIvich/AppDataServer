package com.example.appdataserver;

import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelloController implements Initializable {

    public ListView <String> input;
    public TableView <FileInfo>listServer;
    public TableView <FileInfo>listUser;



    public void quitApp(ActionEvent actionEvent) {

        Platform.exit();
    }
    public void sendToServer(ActionEvent actionEvent) {
    }
    public void sendToUser(ActionEvent actionEvent) {

    }
    public void delFile(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Socket socket = new Socket("localhost",8899);
        } catch (IOException e) {
            e.printStackTrace();
        }


      TableColumn<FileInfo,String>fileTypeColumn = new TableColumn<>();
      fileTypeColumn.setCellValueFactory(param->new SimpleStringProperty(param.getValue().getFileType().getName()));
      fileTypeColumn.setPrefWidth(25);


      TableColumn<FileInfo,String>fileNameColumn = new TableColumn<>("Имя");
      fileNameColumn.setCellValueFactory(param->new SimpleStringProperty(param.getValue().getFilename()));
      fileNameColumn.setPrefWidth(350);

      TableColumn<FileInfo,Long>fileSizeColumn = new TableColumn<>("Размер");
     fileSizeColumn.setCellValueFactory(param->new SimpleObjectProperty<>(param.getValue().getFileSize()));
     fileSizeColumn.setPrefWidth(100);
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







       updateList(Paths.get(".","SFiles"));



    }
    public void updateList(Path path){
        try {
            listServer.getItems().clear();
            listServer.getItems().addAll(Files.list(path).map(FileInfo::new).collect(Collectors.toList()));
            listServer.sort();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,  " Не удалось обновить список файлов", ButtonType.OK);
            alert.showAndWait();

        }

    }

}
