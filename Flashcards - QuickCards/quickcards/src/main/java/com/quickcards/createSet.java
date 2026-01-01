package com.quickcards;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class createSet extends Application{

    public static void main(String[] args ) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/createSet.fxml"));
            Parent root = fxmlLoader.load();
            
            Scene scene = new Scene(root);  
            scene.getStylesheets().add("/createSetStylesheet.css");
            
            primaryStage.setTitle("QuickCards");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            createSetController controller = fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
