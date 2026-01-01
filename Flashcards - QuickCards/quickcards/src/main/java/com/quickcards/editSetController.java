package com.quickcards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editSetController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private int index = 0;
    private String word;
    private String expl;
    private int counterWord = 1;
    private int counterExpl = 1;
    private boolean isNewCard = false;

    ArrayList<String> front = new ArrayList<>();
    ArrayList<String> back = new ArrayList<>();


    @FXML
    private Label setName;

    @FXML
    private TextField wordField;

    @FXML
    private TextArea explArea;

    public void setFront(ArrayList<String> front) {
        this.front = front;
    }

    public void setBack(ArrayList<String> back) {
        this.back = back;
    }

    public void initData(ArrayList<String> front, ArrayList<String> back) {
        this.front = front;
        this.back = back;
        this.index = 0;
        displayCurrentCards();
    }

    
    public void backtoMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBrowse(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/browse.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setName(String newsetName) {
        setName.setText("" + newsetName);
    }

    public void displayCurrentCards() {
        wordField.setText(front.get(index));
        explArea.setText(back.get(index));
    }

    public void nextCard() {
        if (front.isEmpty()) return;

        if (!(index == (front.size())) || !(index == (back.size()))) {
        index = (index + 1) % front.size();
        wordField.setText(front.get(index));
        explArea.setText(back.get(index));
        }
    }

    public void addToSet() throws IOException {
    String word = wordField.getText();
    String expl = explArea.getText();

    if ((!word.isEmpty()) && (!expl.isEmpty())) {
        File directory = new File("sets");
        File file = new File(directory, setName.getText() + ".json");

        JSONObject jsonData;

        if (file.exists()) {
            String content = new String(Files.readAllBytes(file.toPath()));
            jsonData = new JSONObject(content);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No such set exists to edit.");
            alert.show();
            return;
        }

        JSONObject wordEntry = new JSONObject();
        wordEntry.put("word", word);
        wordEntry.put("explanation", expl);

        if (isNewCard) {
            int newEntryIndex = getNextEntryIndex(jsonData);
            String entryKey = "entry" + newEntryIndex;
            jsonData.put(entryKey, wordEntry);

            front.add(word);
            back.add(expl);
            index = front.size() - 1;

            isNewCard = false;
        } else {
            String entryKey = "entry" + (index + 1);
            if (!jsonData.has(entryKey)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Not found");
                alert.setHeaderText(null);
                alert.setContentText("The entry does not exist.");
                alert.show();
                return;
            }

            jsonData.put(entryKey, wordEntry);
            front.set(index, word);
            back.set(index, expl);
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jsonData.toString(4));
        }

    } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WARNING");
        alert.setHeaderText(null);
        alert.setContentText("Please enter both a word and an explanation!");
        alert.show();
    }
    wordField.clear();
    explArea.clear();
}


private int getNextEntryIndex(JSONObject jsonData) {
    int max = 0;
    for (String key : jsonData.keySet()) {
        if (key.startsWith("entry")) {
            try {
                int num = Integer.parseInt(key.substring(5));
                if (num > max) max = num;
            } catch (NumberFormatException e) {
            }
        }
    }
    return max + 1;
}

    public void newCard() {
        wordField.clear();
        explArea.clear();
        isNewCard = true;
    }


public void deleteCard() throws IOException {
    String wordToDelete = wordField.getText().trim();
    String explToDelete = explArea.getText().trim();

    String filePath = "sets\\" + setName.getText() + ".json";
    String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
    JSONObject obj = new JSONObject(jsonContent);

    String keyToRemove = null;

    for (String key : obj.keySet()) {
        Object value = obj.get(key);

        if (value instanceof JSONObject card) {
            String word = card.optString("word", "").trim();
            String explanation = card.optString("explanation", "").trim();

            if (word.equals(wordToDelete) && explanation.equals(explToDelete)) {
                keyToRemove = key;
                break;
            }
        }
    }

    if (keyToRemove != null) {
        obj.remove(keyToRemove);
        Files.write(Paths.get(filePath), obj.toString(2).getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Deleted card with key: " + keyToRemove);

        front.remove(index);
        back.remove(index);

        if (index >= front.size() && !front.isEmpty()) {
            index = front.size() - 1;
        }

        if (!front.isEmpty()) {
            displayCurrentCards();
        } else {
            wordField.clear();
            explArea.clear();
        }
    } else {
        System.out.println("No matching card found to delete.");
    }
}


    public void getFrontList(ArrayList<String> list) {
        front = list;
    } 

    public void getBackList(ArrayList<String> list) {
        back = list;
    }
    

}
