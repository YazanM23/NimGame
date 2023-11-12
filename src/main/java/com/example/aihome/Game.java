package com.example.aihome;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1020, 570);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

}
