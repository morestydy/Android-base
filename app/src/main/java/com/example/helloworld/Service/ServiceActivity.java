package com.example.helloworld.Service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnThread;
    private Button mBtnStop;
    private Button mBtnStart;
    private Button mBtnBindService;
    private Button mBtnUnbindservice;

    private MyService.DownloadBinder downloadBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mBtnThread = findViewById(R.id.btn_thread);
        mBtnThread.setOnClickListener(this);

        mBtnStart = findViewById(R.id.btn_start_service);
        mBtnStop = findViewById(R.id.btn_stop_service);

        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);

        mBtnBindService = findViewById(R.id.btn_bind_service);
        mBtnUnbindservice = findViewById(R.id.btn_unbind_service);

        mBtnBindService.setOnClickListener(this);
        mBtnUnbindservice.setOnClickListener(this);

        findViewById(R.id.btn_startintent_service).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_thread:
                intent = new Intent(ServiceActivity.this,UiUpdateActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_start_service:
                intent = new Intent(ServiceActivity.this,MyService.class);
                startService(intent);
                break;
            case R.id.btn_stop_service:
                intent = new Intent(ServiceActivity.this,MyService.class);
                stopService(intent);
                break;
            case R.id.btn_bind_service:
                intent = new Intent(ServiceActivity.this,MyService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);//绑定服务
                break;
            case R.id.btn_unbind_service:
                unbindService(connection);
                break;
            case R.id.btn_startintent_service:
                Log.d("MainActivity","Thread id is"+Thread.currentThread().getId());
                intent = new Intent(this,MyIntentService.class);
                startService(intent);
                break;
            default:
                break;
        }
    }
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}