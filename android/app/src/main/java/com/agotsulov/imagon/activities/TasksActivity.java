package com.agotsulov.imagon.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.agotsulov.imagon.Profile;
import com.agotsulov.imagon.R;

public class TasksActivity extends AppCompatActivity {

    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        profile = new Profile();
    }

    public void toCamera(View view) {
        String buttonText = ((Button) view).getText().toString();
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra("task", buttonText);
        startActivity(intent);
    }

    public void toGallery(View view) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }
}
