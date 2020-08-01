package com.example.helloworld.ex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

public class ExerciseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        mBtnThread = findViewById(R.id.btn_exThread);

        mBtnThread.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_exThread:
                intent = new Intent(ExerciseActivity.this,ExThreadActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}