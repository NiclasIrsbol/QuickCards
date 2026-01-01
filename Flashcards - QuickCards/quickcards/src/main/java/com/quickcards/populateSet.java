package com.quickcards;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class populateSet extends Application{

    @FXML
    private Button next_btn;

    public static void main(String[] args ) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/populateSet.fxml"));
            Parent root = fxmlLoader.load();
            
            Scene scene = new Scene(root);  
            scene.getStylesheets().add("/populateSetStylesheet.css");
            
            primaryStage.setTitle("QuickCards");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            populateSetController controller = fxmlLoader.getController();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}