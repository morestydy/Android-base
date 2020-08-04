package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.broadcast.BroadActivity;
import com.example.helloworld.datastorage.DataStorageActivity;
import com.example.helloworld.fragment.ContainerActivity;
import com.example.helloworld.jump.AActivity;

public class OtherActivity extends AppCompatActivity {

    private Button mBtnLifeCycle,mBtnJump,mBtnFragment,mBtnEvent,mBtnHandler
            ,mBtnData,mBtnBrocast,mBtnAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        mBtnLifeCycle=findViewById(R.id.btn_lifecycle);
        mBtnJump = findViewById(R.id.btn_btnJump);
        mBtnFragment = findViewById(R.id.btn_Fragment);
        mBtnEvent = findViewById(R.id.btn_event);
        mBtnHandler = findViewById(R.id.btn_handler);
        mBtnData = findViewById(R.id.btn_data);
        mBtnBrocast = findViewById(R.id.btn_brocast);
        mBtnAnim = findViewById(R.id.btn_anim);
        setListeners();
    }

    void setListeners(){
        OnClick onClick = new OnClick();
        mBtnLifeCycle.setOnClickListener(onClick);
        mBtnJump.setOnClickListener(onClick);
        mBtnFragment.setOnClickListener(onClick);
        mBtnEvent.setOnClickListener(onClick);
        mBtnHandler.setOnClickListener(onClick);
        mBtnData.setOnClickListener(onClick);
        mBtnBrocast.setOnClickListener(onClick);
        mBtnAnim.setOnClickListener(onClick);
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
                case R.id.btn_Fragment:
                    intent = new Intent(OtherActivity.this, ContainerActivity.class);
                    break;
                case R.id.btn_event:
                    intent = new Intent(OtherActivity.this, EventActivity.class);
                    break;
                case R.id.btn_handler:
                    intent = new Intent(OtherActivity.this, HandlerActivity.class);
                    break;
                case R.id.btn_data:
                    intent = new Intent(OtherActivity.this, DataStorageActivity.class);
                    break;
                case R.id.btn_brocast:
                    intent = new Intent(OtherActivity.this, BroadActivity.class);
                    break;
                case R.id.btn_anim:
                    intent = new Intent(OtherActivity.this, ObjectAnimActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}