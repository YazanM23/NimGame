package com.example.aihome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class VersionsController {
    @FXML
    private Stage stage;
    private Scene scene;


    private Parent root;
@FXML
    public void goToVersion1(ActionEvent e) throws IOException {
    root = FXMLLoader.load(getClass().getResource("Levels.fxml"));
    stage=(Stage)((Node)e.getSource()).getScene().getWindow();
    scene=new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

}
