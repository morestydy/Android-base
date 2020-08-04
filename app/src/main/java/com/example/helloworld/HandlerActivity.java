package com.example.helloworld;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.util.ToastUtil;

/**
 * com.example.helloworld
 * HelloWorld
 *
 * @author:Tom 2020/8/1
 * 描述:延时3秒处理,跳转界面
 **/

public class HandlerActivity extends AppCompatActivity {
    private static final int UPDATE_TEXT = 1;

    private Handler mhandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        //延时3秒处理,跳转界面
//        mhandler = new Handler();
//        mhandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(HandlerActivity.this,ButtonActivity.class);
//                startActivity(intent);
//            }
//        }, 3000);

        mhandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case UPDATE_TEXT:
                        ToastUtil.showMsg(HandlerActivity.this,"线程通信ok");
                        break;
                }
            }
        };
//        mhandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(HandlerActivity.this,ButtonActivity.class);
//                startActivity(intent);
//            }
//        },5000);
        new Thread(){
            @Override
            public void run() {
                super.run();
                Message message = new Message();
                message.what=UPDATE_TEXT;
                mhandler.sendMessage(message);
            }
        }.start();
    }
}
