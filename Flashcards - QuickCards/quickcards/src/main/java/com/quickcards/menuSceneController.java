package com.quickcards;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class menuSceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    mainSceneController mainscene = new mainSceneController();
    
    public void switchToCreate(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/createSet.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    public void switchToBrowse(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/browse.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public mainSceneController getMainSceneController() {
        return this.mainscene;
    }
    
    public void quit() {
        Platform.exit();
    }

}
