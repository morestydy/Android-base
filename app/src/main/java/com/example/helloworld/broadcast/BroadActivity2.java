package com.example.helloworld.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.helloworld.R;

public class BroadActivity2 extends AppCompatActivity {

    private Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast2);
        mBtn=findViewById(R.id.btn_click);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送广播
                Intent intent = new Intent("com.example.up");
                LocalBroadcastManager.getInstance(BroadActivity2.this).sendBroadcast(intent);
            }
        });
    }
}