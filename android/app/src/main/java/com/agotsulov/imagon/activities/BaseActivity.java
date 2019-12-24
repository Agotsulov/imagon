package com.agotsulov.imagon.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

        profile = Profile.getInstance(getApplication());

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            requestForSpecificPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
        }
        if (!checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestForSpecificPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
        }
    }


    protected boolean checkPermission(String permission) {
        int result = ContextCompat.checkSelfPermission(this, permission);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    protected void requestForSpecificPermission(String[] permissions) {
        ActivityCompat.requestPermissions(this, permissions, 1);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
