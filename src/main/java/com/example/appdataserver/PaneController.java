package com.example.appdataserver;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PaneController implements Initializable {

    public TableView <FileInfo> listUser;
    public ComboBox <String> DisksBox;
    public TextField pathField;


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




        TableColumn<FileInfo,String> fileTypeColumn = new TableColumn<>();
        fileTypeColumn.setCellValueFactory(param->new SimpleStringProperty(param.getValue().getFileType().getName()));
        fileTypeColumn.setPrefWidth(25);


        TableColumn<FileInfo,String>fileNameColumn = new TableColumn<>("Имя");
        fileNameColumn.setCellValueFactory(param->new SimpleStringProperty(param.getValue().getFilename()));
        fileNameColumn.setPrefWidth(350);

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

        listUser.getColumns().addAll(fileTypeColumn,fileNameColumn,fileSizeColumn);
        listUser.getSortOrder().add(fileTypeColumn);


        DisksBox.getItems().clear();
        for (Path p : FileSystems.getDefault().getRootDirectories()){
            DisksBox.getItems().add(p.toString());
        }

        DisksBox.getSelectionModel().select(0);


        listUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount()==2){
                    Path path = Paths.get(pathField.getText()).resolve(listUser.getSelectionModel().getSelectedItem().getFilename());

                    if(Files.isDirectory(path)){
                        updateList(path);
                    }
                }
            }
        });

        updateList(Paths.get("."));
    }
    public void updateList(Path path){
        try {
            pathField.setText(path.normalize().toAbsolutePath().toString());
            listUser.getItems().clear();
            listUser.getItems().addAll(Files.list(path).map(FileInfo::new).collect(Collectors.toList()));
            listUser.sort();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,  " Не удалось обновить список файлов", ButtonType.OK);
            alert.showAndWait();

        }

    }

    public void btnPathAppAction(ActionEvent actionEvent) {

        Path upperPath = Paths.get(pathField.getText()).getParent();

        if(upperPath != null){
            updateList(upperPath);
        }
    }
    public void discChange(ActionEvent actionEvent) {
        ComboBox<String> element = (ComboBox<String>) actionEvent.getSource();
        updateList(Paths.get(element.getSelectionModel().getSelectedItem()));

    }
    public String getSelectedFileName(){
        return listUser.getSelectionModel().getSelectedItem().getFilename();
    }

    public String getCurrentPatch(){
        return  pathField.getText();
    }
}
