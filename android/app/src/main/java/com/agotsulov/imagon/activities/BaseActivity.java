package com.agotsulov.imagon.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.agotsulov.imagon.Profile;
import com.agotsulov.imagon.utils.IOUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseActivity extends AppCompatActivity {

    protected Gson gson = new GsonBuilder().create();
    protected Profile profile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        profile = Profile.getInstance();


    }

}
