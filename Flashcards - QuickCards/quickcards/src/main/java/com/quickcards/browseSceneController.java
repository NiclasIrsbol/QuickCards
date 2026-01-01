package com.quickcards;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class browseSceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label nameLabel1;
    
    @FXML
    private Label nameLabel2;
    
    @FXML
    private Label nameLabel3;
    
    @FXML
    private Label nameLabel4;
    
    @FXML
    private Label nameLabel5;
    
    @FXML
    private Label nameLabel6;

    @FXML
    private Label countLabel1;

    @FXML
    private Label countLabel2;

    @FXML
    private Label countLabel3;

    @FXML
    private Label countLabel4;

    @FXML
    private Label countLabel5;

    @FXML
    private Label countLabel6;

    @FXML
    private Button deleteLabel1;

    @FXML
    private Button deleteLabel2;
    
    @FXML
    private Button deleteLabel3;
    
    @FXML
    private Button deleteLabel4;
    
    @FXML
    private Button deleteLabel5;
    
    @FXML
    private Button deleteLabel6;

    @FXML
    private AnchorPane setBox1;

    @FXML
    private AnchorPane setBox2;
    
    @FXML
    private AnchorPane setBox3;
    
    @FXML
    private AnchorPane setBox4;
    
    @FXML
    private AnchorPane setBox5;

    @FXML
    private AnchorPane setBox6;

    @FXML
    private GridPane gridpane;

    @FXML
    private Button editLabel1;

    @FXML
    private Button editLabel2;

    @FXML
    private Button editLabel3;

    @FXML
    private Button editLabel4;

    @FXML
    private Button editLabel5;

    @FXML
    private Button editLabel6;

    ArrayList<String> frontSidesList;

    ArrayList<String> backSidesList;
    
    Label[] nameLabels;
    Label[] countLabels;

    public void backtoMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        nameLabels = new Label[]{nameLabel1, nameLabel2, nameLabel3, nameLabel4, nameLabel5, nameLabel6};
        countLabels = new Label[]{countLabel1, countLabel2, countLabel3, countLabel4, countLabel5, countLabel6};

        initializeSets();
    }

    public void initializeSets() {
        File folder = new File("sets");
        ObjectMapper mapper = new ObjectMapper();
        File[] jsonFiles = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (jsonFiles == null) return;

        for (int i = 0; i < Math.min(jsonFiles.length, nameLabels.length); i++) {
            try {
        JsonNode root = mapper.readTree(jsonFiles[i]);

        String setName = root.get("setName").asText();

        int count = 0;
        Iterator<Map.Entry<String, JsonNode>> fields = root.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            if (entry.getKey().startsWith("entry")) {
                count++;
            }
        }

        nameLabels[i].setText(setName);
        countLabels[i].setText(count + " card");
        
    } catch (IOException e) {
        nameLabels[i].setText("Error");
        countLabels[i].setText("-");
        e.printStackTrace();
    }
}
    }

    public void setBox1Pressed(MouseEvent event) throws IOException {
        String setName = nameLabel1.getText();
        System.out.println(setName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();
        mainSceneController controller = loader.getController();
        controller.initSetName(setName);
        frontSidesList = getFrontSides(setName);
        backSidesList = getBackSides(setName);
        controller.initData(frontSidesList, backSidesList);
        controller.getFrontList(frontSidesList);
        controller.getBackList(backSidesList);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setBox2Pressed(MouseEvent event) throws IOException {
        String setName = nameLabel2.getText();
        System.out.println(setName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();
        mainSceneController controller = loader.getController();
        controller.initSetName(setName);
        frontSidesList = getFrontSides(setName);
        backSidesList = getBackSides(setName);
        controller.initData(frontSidesList, backSidesList);
        controller.getFrontList(frontSidesList);
        controller.getBackList(backSidesList);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setBox3Pressed(MouseEvent event) throws IOException {
        String setName = nameLabel3.getText();
        System.out.println(setName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();
        mainSceneController controller = loader.getController();
        controller.initSetName(setName);
        frontSidesList = getFrontSides(setName);
        backSidesList = getBackSides(setName);
        controller.initData(frontSidesList, backSidesList);
        controller.getFrontList(frontSidesList);
        controller.getBackList(backSidesList);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setBox4Pressed(MouseEvent event) throws IOException {
        String setName = nameLabel4.getText();
        System.out.println(setName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();
        mainSceneController controller = loader.getController();
        controller.initSetName(setName);
        frontSidesList = getFrontSides(setName);
        backSidesList = getBackSides(setName);
        controller.initData(frontSidesList, backSidesList);
        controller.getFrontList(frontSidesList);
        controller.getBackList(backSidesList);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setBox5Pressed(MouseEvent event) throws IOException {
        String setName = nameLabel5.getText();
        System.out.println(setName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();
        mainSceneController controller = loader.getController();
        controller.initSetName(setName);
        frontSidesList = getFrontSides(setName);
        backSidesList = getBackSides(setName);
        controller.initData(frontSidesList, backSidesList);
        controller.getFrontList(frontSidesList);
        controller.getBackList(backSidesList);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setBox6Pressed(MouseEvent event) throws IOException {
        String setName = nameLabel6.getText();
        System.out.println(setName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();
        mainSceneController controller = loader.getController();
        controller.initSetName(setName);
        frontSidesList = getFrontSides(setName);
        backSidesList = getBackSides(setName);
        controller.initData(frontSidesList, backSidesList);
        controller.getFrontList(frontSidesList);
        controller.getBackList(backSidesList);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setDelete(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        if (clickedButton == deleteLabel1) {
            gridpane.getChildren().remove(setBox1);
            deleteFile(nameLabel1.getText());
        } else if (clickedButton == deleteLabel2) {
            gridpane.getChildren().remove(setBox2);
            deleteFile(nameLabel2.getText());
        } else if (clickedButton == deleteLabel3) {
            gridpane.getChildren().remove(setBox3);
            deleteFile(nameLabel3.getText());
        } else if (clickedButton == deleteLabel4) {
            gridpane.getChildren().remove(setBox4);
            deleteFile(nameLabel4.getText());
        } else if (clickedButton == deleteLabel5) {
            gridpane.getChildren().remove(setBox5);
            deleteFile(nameLabel5.getText());
        } else if (clickedButton == deleteLabel6) {
            gridpane.getChildren().remove(setBox6);
            deleteFile(nameLabel6.getText());
        }
    }

    public void setEdit(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton == editLabel1) {
            String name = nameLabel1.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit.fxml"));
            Parent root = loader.load();
            editSetController controller = loader.getController();
            controller.setName(name);
            frontSidesList = getFrontSides(name);
            backSidesList = getBackSides(name);
            controller.initData(frontSidesList, backSidesList);
            controller.getFrontList(frontSidesList);
            controller.getBackList(backSidesList);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else if (clickedButton == editLabel2) {
            String name = nameLabel2.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit.fxml"));
            Parent root = loader.load();
            editSetController controller = loader.getController();
            controller.setName(name);
            frontSidesList = getFrontSides(name);
            backSidesList = getBackSides(name);
            controller.initData(frontSidesList, backSidesList);
            controller.getFrontList(frontSidesList);
            controller.getBackList(backSidesList);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else if (clickedButton == editLabel3) {
            String name = nameLabel3.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit.fxml"));
            Parent root = loader.load();
            editSetController controller = loader.getController();
            controller.setName(name);
            frontSidesList = getFrontSides(name);
            backSidesList = getBackSides(name);
            controller.initData(frontSidesList, backSidesList);
            controller.getFrontList(frontSidesList);
            controller.getBackList(backSidesList);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else if (clickedButton == editLabel4) {
            String name = nameLabel4.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit.fxml"));
            Parent root = loader.load();
            editSetController controller = loader.getController();
            controller.setName(name);
            frontSidesList = getFrontSides(name);
            backSidesList = getBackSides(name);
            controller.initData(frontSidesList, backSidesList);
            controller.getFrontList(frontSidesList);
            controller.getBackList(backSidesList);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else if (clickedButton == editLabel5) {
            String name = nameLabel5.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit.fxml"));
            Parent root = loader.load();
            editSetController controller = loader.getController();
            controller.setName(name);
            frontSidesList = getFrontSides(name);
            backSidesList = getBackSides(name);
            controller.initData(frontSidesList, backSidesList);
            controller.getFrontList(frontSidesList);
            controller.getBackList(backSidesList);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else if (clickedButton == editLabel6) {
            String name = nameLabel6.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit.fxml"));
            Parent root = loader.load();
            editSetController controller = loader.getController();
            controller.setName(name);
            frontSidesList = getFrontSides(name);
            backSidesList = getBackSides(name);
            controller.initData(frontSidesList, backSidesList);
            controller.getFrontList(frontSidesList);
            controller.getBackList(backSidesList);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void deleteFile(String setName) {
        File directory = new File("sets");
        File[] files = directory.listFiles();
        for (File f : files)
        {
        if (f.getName().startsWith(setName))
        {
        f.delete();
        }
    }
    }

    public ArrayList<String> getFrontSides(String setName) {
        ArrayList<String> words = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new File("sets\\" + setName + ".json"));
            Iterator<Map.Entry<String, JsonNode>> fields = root.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String key = entry.getKey();

                if (key.startsWith("entry")) {
                    JsonNode node = entry.getValue();
                    words.add(node.get("word").asText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }

    public ArrayList<String> getBackSides(String setName) {
        ArrayList<String> explanations = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new File("sets\\" + setName + ".json"));
            Iterator<Map.Entry<String, JsonNode>> fields = root.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String key = entry.getKey();
                if (key.startsWith("entry")) {
                    JsonNode node = entry.getValue();
                    explanations.add(node.get("explanation").asText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return explanations;
    }
}