package com.fic.cursoandroid2025g4.view.task;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.cursoandroid2025g4.R;
import com.fic.cursoandroid2025g4.controller.task.TaskController;
import com.fic.cursoandroid2025g4.model.task.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity {

    private TaskAdapter taskAdapter;
    private RecyclerView recyclerView;
    private TaskController taskController;
    private FloatingActionButton fabCrear;

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
        // Obtiene la lista actualizada de tareas desde el controlador
        List<Task> updatedTasks = taskController.getAllTasks();
        // Pasa la nueva lista al adaptador
        taskAdapter.setData(updatedTasks);
    }

    public void initComponents() {
        fabCrear = findViewById(R.id.fabCrear);

        fabCrear.setOnClickListener(v -> {

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
            taskController.addTask(etTitle.getText().toString(), etDescription.getText().toString(), "10/10/2025", false);
            dialog.dismiss();
            refreshTaskList();
        });

        dialog.show();
    }

    public void showTaskDialog(Task task) {
        Dialog dialog = new Dialog(this);
        View view = getLayoutInflater().inflate(R.layout.task_dialog, null);
        dialog.setContentView(view);

        AppCompatButton btnAddTask = view.findViewById(R.id.btnAddTask);
        EditText etTitle = view.findViewById(R.id.etTitle);
        EditText etDescription = view.findViewById(R.id.etDescription);

        btnAddTask.setText("Editar tarea");
        etTitle.setText(task.task_title);
        etDescription.setText(task.task_description);

        btnAddTask.setOnClickListener(V -> {
            taskController.updateTask(task.id, etTitle.getText().toString(), etDescription.getText().toString(), task.created_at, task.is_completed);
            dialog.dismiss();
            refreshTaskList();
        });

        dialog.show();
    }

}