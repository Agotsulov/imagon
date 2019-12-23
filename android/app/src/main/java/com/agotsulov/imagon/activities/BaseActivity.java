package com.agotsulov.imagon.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.agotsulov.imagon.Profile;

public class BaseActivity extends AppCompatActivity {

    protected Profile profile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        profile = Profile.getInstance();
    }

    @Override
    protected void onStop() {
        super.onStop();
        profile.save();
    }
}
