package com.example.aihome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LevelsController {
    @FXML
    private VBox Levels;
    @FXML
    private Button Easy;

    @FXML
    private Button Hard;


    @FXML
    private Button Medium;
    @FXML
    private TextField sticks;

    @FXML
    private Stage stage;
    private Scene scene;


    private Parent root;
    int value=0;

    @FXML
    protected void onButtonsClickOpenGame(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));

        // Create an instance of the controller and set the value
        GameController gameController = new GameController();
        gameController.number(value);

        // Set the controller for the loader
        loader.setController(gameController);

        // Load the FXML file
        root = loader.load();

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    @FXML
    protected void GoBack(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void easyMode(ActionEvent e) throws IOException {

        sticks.setStyle("-fx-border-color: white ;-fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent;");

        try{
            value= Integer.parseInt(sticks.getText());
            if(value>15 || value<3){
                value=-1;
            }

        }
        catch (Exception ex){
            value=-1;
        }
        if(value!=-1){
            onButtonsClickOpenGame(e);
        }
        else {
            sticks.setStyle("-fx-border-color:red ;-fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent;");

        }


    }

}
