package com.agotsulov.imagon.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.agotsulov.imagon.ImagonException;
import com.agotsulov.imagon.Profile;
import com.agotsulov.imagon.R;
import com.agotsulov.imagon.Task;
import com.agotsulov.imagon.TaskGenerator;
import com.agotsulov.imagon.magic.Vocabulary;
import com.agotsulov.imagon.utils.IOUtils;

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

    public void refreshTask(View view) {
        int buttonId = ((ImageButton) view).getId();
        if (profile.getCoins() >= 100) {
            Task task = taskGenerator.generate();
            if (buttonId == R.id.refresh1) {
                profile.setTask(0, task);
            } else if (buttonId == R.id.refresh2) {
                profile.setTask(1, task);
            } else if (buttonId == R.id.refresh3) {
                profile.setTask(2, task);
            }
            profile.addCoins(-100);
            updateTasks();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTasks();
    }

    private void updateTask(int i) {
        taskButtons[i].setText(profile.getCurrentTasks().get(i).getTaskText());
    }

    private void updateTasks() {
        info.setText(profile.getCoinsText());

        while (profile.getCurrentTasks().size() < COUNT_TASKS) {
            Task task = taskGenerator.generate();
            profile.addTask(task);
        }

        updateTask(0);
        updateTask(1);
        updateTask(2);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        profile.save(getApplication());
    }
}
