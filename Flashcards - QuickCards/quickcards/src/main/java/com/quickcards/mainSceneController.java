package com.quickcards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class mainSceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label flashcardLabel;

    @FXML
    private StackPane flashcardsBox;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button next_btn;

    @FXML
    private Label progressText;

    @FXML
    private Label setLabel;
    private String firstsetName;

    private boolean isBack;
    private List<Flashcard> flashcardsList = new ArrayList<>();
    private List<String> setNames = new ArrayList<>();
    private List<List<Flashcard>> flashcardsSets = new ArrayList<>();
    private int currentCard = 0;
    private double progress = 1;

    ArrayList<String> front = new ArrayList<>();
    ArrayList<String> back = new ArrayList<>();

    
    public void setFront(ArrayList<String> front) {
        this.front = front;
    }

    public void setBack(ArrayList<String> back) {
        this.back = back;
    }

    public void initData(ArrayList<String> front, ArrayList<String> back) {
        this.front = front;
        this.back = back;
        initializeFlashcards();
}

    public void generateFlashcards() {
    flashcardsList.clear();
    for (int i = 0; i < Math.min(front.size(), back.size()); i++) {
        Flashcard flashcard = new Flashcard(front.get(i), back.get(i));
        flashcardsList.add(flashcard);
    }
    }

    public void initialize() {
        flashcardsBox.setOnMouseEntered(event -> flashcardsBox.setCursor(Cursor.HAND));
        flashcardsBox.setOnMouseExited(event -> flashcardsBox.setCursor(Cursor.DEFAULT));
        next_btn.setOnAction(e -> nextCard());
    }

    private void initializeFlashcards() {
        generateFlashcards();
        try {
        if (flashcardsList.isEmpty()) {
            flashcardLabel.setText("No flashcards available");
            progressText.setText("0 / 0");
            return;
        }
        } catch (Exception e) {

        }
        
        Collections.shuffle(this.flashcardsList);
        
        setNames.add(firstsetName);
        flashcardLabel.setText(flashcardsList.get(currentCard).getFront());
        progressText.setText(currentCard + " / " + (flashcardsList.size())/2);
        flashcardsBox.setOnMouseClicked(this::handleFlashcardClick);
        
        updateProgressBar();
    }

    public void flipCard(Flashcard flashcard) {
        if (isBack) { 
            flashcardLabel.setText(flashcard.getFront());
            isBack = false;
            flashcardsBox.setStyle("-fx-background-color: #00B7FF;");
        } else {
            flashcardLabel.setText(flashcard.getBack());
            isBack = true;
            flashcardsBox.setStyle("-fx-background-color: #37DF85;");
        }
    }
    
    public void nextCard() {
        if (flashcardsList.isEmpty()) return;

        flashcardsBox.setOnMouseClicked(this::handleFlashcardClick);
        if (!(currentCard == (front.size()))) {
        currentCard = (currentCard + 1) % front.size();
        flashcardLabel.setText(flashcardsList.get(currentCard).getFront());
        isBack = false;
        flashcardsBox.setStyle("-fx-background-color: #00B7FF;");

        updateProgressBar();
        }
    }

    public void prevCard() {
        if (flashcardsList.isEmpty()) return;
        
        flashcardsBox.setOnMouseClicked(this::handleFlashcardClick);
        try {
        if (!(currentCard == 1)) {
        currentCard = (currentCard - 1) % front.size();
        flashcardLabel.setText(flashcardsList.get(currentCard).getFront());
        isBack = false;
        flashcardsBox.setStyle("-fx-background-color: #00B7FF;");
        updateProgressBar();
        }
        } catch (Exception e) {
        }
    }

    public void handleFlashcardClick(MouseEvent event) {
        if (flashcardsList.isEmpty()) return;

        flashcardsBox.setOnMouseClicked(null);
        flipCard(flashcardsList.get(currentCard));
        flipAnimation();
    }

    private void flipAnimation() {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(flashcardsBox);
        rotateTransition.setDuration(Duration.seconds(0.5));
        rotateTransition.setByAngle(180);

        rotateTransition.setAxis(javafx.geometry.Point3D.ZERO.add(0, 1, 0));

        rotateTransition.setOnFinished(event -> {
            flashcardsBox.setOnMouseClicked(null);
        });
        RotateTransition labelRotate = new RotateTransition(Duration.seconds(0.5), flashcardLabel);
        labelRotate.setByAngle(180);
        labelRotate.setAxis(new Point3D(0, 1, 0));

        rotateTransition.play();
        labelRotate.play();
    }

    private void updateProgressBar() {
        if (flashcardsList.isEmpty()) {
            progressBar.setProgress(0);
            progressText.setText("0 / 0");
            return;
        }
        progress = (double) currentCard / ((front.size()-1));
        progressBar.setProgress(progress);
        progressBar.setStyle("-fx-accent: #37DF85");
        progressText.setText((currentCard + 1) + " / " + (front.size()));


    }

    public List<List<Flashcard>> getSet() {
        return flashcardsSets;
    }

    public List<String> getSetNames() {
        return setNames;
    }

    public void initSetName(String setName) {
        setLabel.setText("Category: " + setName);
    }

    public void backtoBrowse(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/browse.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void getFrontList(ArrayList<String> list) {
        front = list;
        for (String s : front) {
            System.out.println(s);
        }
    } 

    public void getBackList(ArrayList<String> list) {
        back = list;
        for (String s : back) {
            System.out.println(s);
        }
    }

}
