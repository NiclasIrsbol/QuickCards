package com.quickcards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class createSetController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    String fileName;

    @FXML
    private TextField nameField;

    @FXML
    private Label statusLabel;
    
    public void backtoMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void createJSON(ActionEvent event) throws IOException {
        if (!(getNumberOfSets() == 6)) {
            fileName = nameField.getText();
        if (!fileName.isEmpty()) {
            File directory = new File("sets");
            File file = new File(directory, fileName + ".json");
            JSONObject jsonData = new JSONObject();
            jsonData.put("setName", fileName);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(jsonData.toString(4)); 
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a name for set!");
            alert.show();
        }
        switchToPopulate(event, fileName);
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Cannot create more than 6 sets! Please delete a set");
            alert.show();
        }
    }

    public void switchToPopulate(ActionEvent event, String filename) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/populateSet.fxml"));
        root = loader.load();
        populateSetController controller = loader.getController();
        controller.setFileName(fileName);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String getFileName() {
        return fileName;
    }

    public int getNumberOfSets() {
        int count = 0;
        File folder = new File("sets");
        ObjectMapper mapper = new ObjectMapper();
        File[] jsonFiles = folder.listFiles((dir, name) -> name.endsWith(".json"));
        count = jsonFiles.length;
        return count;
    }
}

