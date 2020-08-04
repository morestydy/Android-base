package com.example.helloworld.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.helloworld.R;

public class BroadActivity extends AppCompatActivity {

    private Button mBtn1;
    private TextView mTvTest;
    private MyBrocast mBrocast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcatst);
        mBtn1=findViewById(R.id.btn1);
        mTvTest = findViewById(R.id.tv_text);

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BroadActivity.this,BroadActivity2.class);
                startActivity(intent);
            }
        });
        mBrocast = new MyBrocast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.up");
        LocalBroadcastManager.getInstance(this).registerReceiver(mBrocast,intentFilter);
    }

    private class MyBrocast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "com.example.up":
                    mTvTest.setText("123");
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBrocast);
    }
}