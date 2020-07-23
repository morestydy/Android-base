package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button mBtnUI;
    private Button mBtnOth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnUI = findViewById(R.id.btn_ui);
        Onclick onclick = new Onclick();
        mBtnUI.setOnClickListener(onclick);
//        mBtnOth.setOnClickListener(onclick);
    }

    class Onclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_ui:
                    intent = new Intent(MainActivity.this, UIActivity.class);
                    break;
//                case R.id.btn_others:
//                    intent = new Intent(MainActivity.this,)
            }

            startActivity(intent);
        }
    }
}
