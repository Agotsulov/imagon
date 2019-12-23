package com.agotsulov.imagon;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private List<String> words;

    public Task(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    public String getTaskText() {
        StringBuilder sb = new StringBuilder();
        for(String s: words) {
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public int check(String result) {
        int right = 0;

        for (String s: words) {
            if (result.contains(s)) {
                right++;
            }
        }

        return right;
    }

}
