package com.fic.cursoandroid2025g4.view.task;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.cursoandroid2025g4.R;
import com.fic.cursoandroid2025g4.controller.task.TaskController;
import com.fic.cursoandroid2025g4.model.task.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class TaskActivity extends AppCompatActivity {

    private TaskAdapter taskAdapter;
    private RecyclerView recyclerView;
    private TaskController taskController;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        initRV();
        initComponents();

    }

    public void initRV() {

        recyclerView = findViewById(R.id.rvTask);
        taskController = new TaskController(this);
        taskAdapter = new TaskAdapter(new TaskAdapter.OnTaskActionListener() {

            @Override
            public void onEdit(Task task) {
                showTaskDialog(task);
            }

            @Override
            public void onDelete(Task task) {
                taskController.deleteTask(task);
                refreshTaskList();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.setData(taskController.getAllTasks());

    }

    public void refreshTaskList() {
        List<Task> updatedTasks = taskController.getAllTasks();
        taskAdapter.setData(updatedTasks);
    }

    public void initComponents() {
        fabAdd = findViewById(R.id.fabCrear);

        fabAdd.setOnClickListener(v -> {

            showTaskDialog();

        });
    }

    public void showTaskDialog() {
        Dialog dialog = new Dialog(this);
        View view = getLayoutInflater().inflate(R.layout.task_dialog, null);
        dialog.setContentView(view);

        AppCompatButton btnAddTask = view.findViewById(R.id.btnAddTask);
        EditText etTitle = view.findViewById(R.id.etTitle);
        EditText etDescription = view.findViewById(R.id.etDescription);


        btnAddTask.setOnClickListener(V -> {
            if (validateFields(etTitle, etDescription)){
                taskController.addTask(etTitle.getText().toString(), etDescription.getText().toString(), getCurrentDate(), false);
                dialog.dismiss();
                refreshTaskList();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void showTaskDialog(Task task) {
        Dialog dialog = new Dialog(this);
        View view = getLayoutInflater().inflate(R.layout.task_dialog, null);
        dialog.setContentView(view);

        AppCompatButton btnAddTask = view.findViewById(R.id.btnAddTask);
        EditText etTitle = view.findViewById(R.id.etTitle);
        EditText etDescription = view.findViewById(R.id.etDescription);

        btnAddTask.setText(getText(R.string.BTN_EDIT_TASK));
        etTitle.setText(task.task_title);
        etDescription.setText(task.task_description);

        btnAddTask.setOnClickListener(V -> {
            if (validateFields(etTitle, etDescription)){
                taskController.updateTask(task.id, etTitle.getText().toString(), etDescription.getText().toString(), task.created_at, task.is_completed);
                dialog.dismiss();
                refreshTaskList();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private boolean validateFields(EditText etTitle, EditText etDescription) {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (title.isEmpty()) {
            etTitle.setError(getString(R.string.validate_title));
            return false;
        }

        if (description.isEmpty()) {
            etDescription.setError(getString(R.string.validate_description));
            return false;
        }

        return true;
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }


}