package com.agotsulov.imagon.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agotsulov.imagon.ImagonException;
import com.agotsulov.imagon.Profile;
import com.agotsulov.imagon.R;
import com.agotsulov.imagon.Task;
import com.agotsulov.imagon.magic.Model;
import com.agotsulov.imagon.magic.Vocabulary;
import com.agotsulov.imagon.utils.ImagePicker;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CameraActivity extends BaseActivity {


    static final int REQUEST_TAKE_PHOTO = 1;

    private int currentTaskId;

    private int correct;
    private int need;

    private ImageView resultImageView;
    private TextView resultTextView;

    private TextView taskTextView;

    private Bitmap resizedBitmap;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        if (!checkPermission(Manifest.permission.CAMERA)) {
            requestForSpecificPermission(new String[]{Manifest.permission.CAMERA});
        }

        try {
            resizedBitmap = BitmapFactory.decodeStream(getAssets().open("image.jpg"));
        } catch (IOException e) {
            finish();
        }
//        resizedBitmap = Bitmap.createScaledBitmap(resizedBitmap,
//                224, 224, false);

        this.resultImageView = findViewById(R.id.image);
        this.resultImageView.setImageBitmap(resizedBitmap);

        this.resultTextView = findViewById(R.id.result);

        try {
            Vocabulary vocabulary = new Vocabulary(
                    new InputStreamReader(getAssets().open("words.txt"), "UTF-8")
            );
            this.model = new Model(this, vocabulary);
        } catch (ImagonException | IOException e) {
            e.printStackTrace();
        }

        currentTaskId = getIntent().getIntExtra("task", 0);

        need = profile.getCurrentTasks().get(currentTaskId).getWords().size();

        this.taskTextView = findViewById(R.id.task);
        this.taskTextView.setText(profile.getCurrentTasks().get(currentTaskId).getTaskText());
    }

    public void onShoot(View view) {
        startCameraActivity();
    }

    public void onSubmit(View view) {
        if (correct >= need) {
            this.profile.setTaskDone(currentTaskId);
            this.profile.addCoins(300);
            finish();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                      correct + "/" + need,
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }


    public void startCameraActivity(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, REQUEST_TAKE_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                Bitmap originalBitmap = ImagePicker.getImageFromResult(this, resultCode, data);
                this.resizedBitmap = Bitmap.createScaledBitmap(originalBitmap,
                        224, 224, false);
                this.resultImageView.setImageBitmap(originalBitmap);
                String result = model.forward(resizedBitmap);
                this.resultTextView.setText(result);
                correct = profile.getCurrentTasks().get(currentTaskId).check(result);
            }
        }
    }

}
