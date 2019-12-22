package com.agotsulov.imagon;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private List<String> words;
    private List<String> solutions;
    private boolean done;

    public Task(List<String> words) {
        this.words = words;
        this.solutions = new ArrayList<>();
        this.done = false;
    }

    public void addSolution(String pathToPhoto) {
        this.solutions.add(pathToPhoto);
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<String> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<String> solutions) {
        this.solutions = solutions;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
