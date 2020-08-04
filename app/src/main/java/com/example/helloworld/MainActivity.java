package com.example.helloworld;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.helloworld.ex.ExerciseActivity;

public class MainActivity extends AppCompatActivity {
    private Button mBtnUI;
    private Button mBtnOth,mBtnExercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnUI = findViewById(R.id.btn_ui);
        mBtnOth = findViewById(R.id.btn_others);
        mBtnExercise = findViewById(R.id.btn_exercise);
        Onclick onclick = new Onclick();
        mBtnUI.setOnClickListener(onclick);
        mBtnOth.setOnClickListener(onclick);
        mBtnExercise.setOnClickListener(onclick);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }

    class Onclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_ui:
                    intent = new Intent(MainActivity.this, UIActivity.class);
                    break;
                case R.id.btn_others:
                    intent = new Intent(MainActivity.this,OtherActivity.class);
                    break;
                case R.id.btn_exercise:
                    intent = new Intent(MainActivity.this, ExerciseActivity.class);
                    break;
            }

            startActivity(intent);
        }
    }
}
