package com.fic.cursoandroid2025g4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fic.cursoandroid2025g4.view.task.TaskActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    public Button btnToDo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupClickListener();

    }

    private void initViews(){
        btnToDo = findViewById(R.id.btnPrimeraActividad);
    }

    private void setupClickListener(){

        btnToDo.setOnClickListener(v -> {

            goToActivity();

        });

    }

    private void goToActivity(){

        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        startActivity(intent);

    }
}