package com.agotsulov.imagon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Profile {

    private int coins;
    private List<Task> currentTasks;
    private List<Task> history;

    public Profile() {
        this.coins = 0;
        this.currentTasks = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    public Profile(int coins, List<Task> currentTasks, List<Task> history) {
        this.coins = coins;
        this.currentTasks = currentTasks;
        this.history = history;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public List<Task> getCurrentTasks() {
        return currentTasks;
    }

    public void setCurrentTasks(List<Task> currentTasks) {
        this.currentTasks = currentTasks;
    }

    public List<Task> getHistory() {
        return history;
    }

    public void setHistory(List<Task> history) {
        this.history = history;
    }


}

