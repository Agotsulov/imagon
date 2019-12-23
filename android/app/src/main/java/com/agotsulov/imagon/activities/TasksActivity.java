package com.agotsulov.imagon.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agotsulov.imagon.ImagonException;
import com.agotsulov.imagon.Profile;
import com.agotsulov.imagon.R;
import com.agotsulov.imagon.Task;
import com.agotsulov.imagon.TaskGenerator;
import com.agotsulov.imagon.magic.Vocabulary;

import java.io.IOException;
import java.io.InputStreamReader;

public class TasksActivity extends BaseActivity {

    private final int COUNT_TASKS = 3;

    private TaskGenerator taskGenerator;

    private Button[] taskButtons;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        try {
            taskGenerator = new TaskGenerator(
                    new Vocabulary(
                            new InputStreamReader(getAssets().open("taskWords.txt"), "UTF-8")
                    )
            );
        } catch (ImagonException | IOException e) {
            Log.e("TasksActivity", "Files not found");
            e.printStackTrace();
        }

        info = findViewById(R.id.profileInfo);

        taskButtons = new Button[COUNT_TASKS];

        taskButtons[0] = findViewById(R.id.task1);
        taskButtons[1] = findViewById(R.id.task2);
        taskButtons[2] = findViewById(R.id.task3);
    }

    public void toCamera(View view) {
        int buttonId = ((Button) view).getId();
        Intent intent = new Intent(this, CameraActivity.class);
        if (buttonId == R.id.task1) {
            intent.putExtra("task", 0);
        }
        if (buttonId == R.id.task2) {
            intent.putExtra("task", 1);
        }
        if (buttonId == R.id.task3) {
            intent.putExtra("task", 2);
        }
        startActivity(intent);
    }

    public void toGallery(View view) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTasks();
    }

    private void updateTasks() {
        info.setText(profile.getCoinsText());

        while (profile.getCurrentTasks().size() < COUNT_TASKS) {
            Task task = taskGenerator.generate();
            profile.addTask(task);
        }

        taskButtons[0].setText(profile.getCurrentTasks().get(0).getTaskText());
        taskButtons[1].setText(profile.getCurrentTasks().get(1).getTaskText());
        taskButtons[2].setText(profile.getCurrentTasks().get(2).getTaskText());
    }
}
