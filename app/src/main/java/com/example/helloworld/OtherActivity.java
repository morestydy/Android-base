package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.jump.AActivity;

public class OtherActivity extends AppCompatActivity {

    private Button mBtnLifeCycle,mBtnJump;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        mBtnLifeCycle=findViewById(R.id.btn_lifecycle);
        mBtnJump = findViewById(R.id.btn_btnJump);

        OnClick onClick = new OnClick();
        mBtnLifeCycle.setOnClickListener(onClick);
        mBtnJump.setOnClickListener(onClick);
    }

    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_lifecycle:
                    intent = new Intent(OtherActivity.this,LifeCycleActivity.class);
                    break;
                case R.id.btn_btnJump:
                    intent = new Intent(OtherActivity.this, AActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}