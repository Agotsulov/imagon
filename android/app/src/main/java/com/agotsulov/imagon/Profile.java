package com.agotsulov.imagon;

import android.content.Context;
import android.util.Log;

import com.agotsulov.imagon.utils.IOUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Profile {

    private transient final static Gson gson = new GsonBuilder().create();
    private transient final static String FILENAME = "profile.json";


    private int coins;
    private List<Task> currentTasks;

    public Profile() {
        this.coins = 10000;
        this.currentTasks = new ArrayList<>();
    }

    public static void load(Context context) {
        Log.i("Profile", "LOAD");
        if (IOUtils.isFileExist(context, FILENAME)) {
            String json = IOUtils.read(context, FILENAME);
            Log.i("JSON", json);
            instance = gson.fromJson(
                    json,
                    Profile.class
            );
            Log.i("PROFILE", instance.getCurrentTasks().toString());
            Log.i("PROFILE", instance.getCoins() + "");
        } else {
            instance = new Profile();
        }
    }

    public static void save(Context context) {
        String json =  gson.toJson(instance);
        Log.i("JSON", json);
        IOUtils.write(context, FILENAME, json);
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

    public static Profile getInstance(Context context) {
        if (instance == null) {
            load(context);
        }
        return instance;
    }

}

