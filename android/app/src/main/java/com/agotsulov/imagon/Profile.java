package com.agotsulov.imagon;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Profile {

    private int coins;
    private List<Task> currentTasks;

    public Profile() {
        this.coins = 10000;
        this.currentTasks = new ArrayList<>();
    }

    public void load() {
        Log.i("Profile", "LOAD");
    }

    public void save() {
        Log.i("Profile", "SAVE");
    }

    public void setTask(int i, Task task) {
        currentTasks.set(i, task);
    }

    public void addTask(Task task) {
        currentTasks.add(task);
    }

    public void setTaskDone(int i) {
        currentTasks.remove(i);
    }

    public void addCoins(int count) {
        coins += count;
    }

    public int getCoins() {
        return coins;
    }

    public String getCoinsText() {
        return "Coins: " + coins;
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

    private static Profile instance = null;

    public static Profile getInstance() {
        if (instance == null) {
            instance = new Profile();
        }
        return instance;
    }

}

