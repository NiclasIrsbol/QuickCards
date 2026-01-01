package com.quickcards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class populateSetController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String fileName;
    private String word;
    private String expl;
    private int counterWord = 1;
    private int counterExpl = 1;
    @FXML 
    private Label setName;

    @FXML
    private TextField wordField;

    @FXML
    private TextArea explArea;

    public void backtoMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        if (setName != null) { 
            setName.setText(fileName);
        }
    }

    public void populateJSON(ActionEvent event) throws IOException {
        word = wordField.getText();
        expl = explArea.getText();
    
        if ((!word.isEmpty()) && (!expl.isEmpty())) {
            File directory = new File("sets");
    
            File file = new File(directory, fileName + ".json");
    
            JSONObject jsonData;

            if (file.exists()) {
                String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
                jsonData = new JSONObject(content);
            } else {
                jsonData = new JSONObject(); 
            }
    
            JSONObject wordEntry = new JSONObject();
            wordEntry.put("word", word);
            wordEntry.put("explanation", expl);
    
            jsonData.put("entry" + counterWord, wordEntry);
    
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(jsonData.toString(4));
            } catch (IOException e) {
                e.printStackTrace();
            }
            resetLabels();
            counterWord++; 
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Please enter both a word and an explanation!");
            alert.show();
        }
    }

    public void switchToBrowse(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/browse.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void resetLabels() {
        wordField.setText("");
        explArea.setText("");
        word = "";
        expl = "";
    }

}
