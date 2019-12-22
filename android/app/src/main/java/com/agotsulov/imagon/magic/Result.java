package com.agotsulov.imagon.magic;

public class Result {

    private String task;
    private String text;
    private int correct;
    private int all;

    public Result(String task, String text, int correct, int all) {
        this.task = task;
        this.text = text;
        this.correct = correct;
        this.all = all;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
