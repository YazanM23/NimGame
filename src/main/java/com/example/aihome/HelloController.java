package com.example.aihome;


import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;

import java.io.IOException;

public class HelloController {
    @FXML
    private ImageView loading;
    @FXML
    private Button Play;
    @FXML
    private Stage stage;
    private Scene scene;


    private Parent root;
    @FXML
    protected void onPlayButtonClick(ActionEvent e) {

loading.setVisible(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> {

             root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("Versions.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage=(Stage)((Node)e.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
           loading.setVisible(false);
            Play.setVisible(false);
        });

        delay.play();
    }


}