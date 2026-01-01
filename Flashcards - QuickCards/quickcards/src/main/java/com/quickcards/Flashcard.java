package com.quickcards;

public class Flashcard {
    private String front;
    private String back;

    public Flashcard(String front, String back) {
        this.front = front;
        this.back = back;
    }

    public String getFront() {
        return this.front;
    }

    public String getBack() {
        return this.back;
    }

    public void setFront(String newFront) {
        this.front = newFront;
    }

    public void setBack(String newBack) {
        this.back = newBack;
    }

}
