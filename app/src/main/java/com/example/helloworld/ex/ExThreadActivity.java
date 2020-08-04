package com.example.helloworld.ex;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

public class ExThreadActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mText,mText2;
    private static final int UPDATE_TEXT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_thread);
        mText = findViewById(R.id.tv_text);
        mText2 = findViewById(R.id.tv_text2);

        Button changeText = findViewById(R.id.btn_changetext);
        Button changeText2 = findViewById(R.id.btn_changetext2);
        changeText.setOnClickListener(this);
        changeText2.setOnClickListener(this);

    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    // TODO: 2020/8/1  
                    mText2.setText("Nice to meet u!");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_changetext:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mText.setText("Nice u!");
                    }
                }).start();
                break;
            case R.id.btn_changetext2:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
            default:
                break;
        }
    }


}