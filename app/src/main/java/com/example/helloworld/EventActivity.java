package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.util.ToastUtil;
import com.example.helloworld.widget.MyButton;

/**
 * com.example.helloworld
 * HelloWorld
 *
 * @author:Tom 2020/8/1
 * 描述:
 **/

public class EventActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnEvent;
    private MyButton mBtnMy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event);
        mBtnEvent = findViewById(R.id.btn_event);
        mBtnMy=findViewById(R.id.btn_my);

        mBtnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Listener","----onClick---");
            }
        });
        mBtnMy.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("Listener","----onLongClick---");
                return true;
            }
        });
        //1. 通过内部类实现
        mBtnEvent.setOnClickListener(new Onclick());
        //2. 通过匿名内部类
//        mBtnEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtil.showMsg(EventActivity.this,"通过匿名内部类,Click...");
//            }
//        });
        //3. 通过事件源所在类实现
//        mBtnEvent.setOnClickListener(EventActivity.this);

        //4. 通过外部类
//        mBtnEvent.setOnClickListener(new MyClickListener(EventActivity.this));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_event:
                ToastUtil.showMsg(EventActivity.this,"通过事件源所在类实现,Click...");
        }
    }

    class Onclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_event:
                    ToastUtil.showMsg(EventActivity.this,"通过内部类实现,Click...");
                    break;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("Activity","----onTouchEvent---");
                break;
        }
        return false;
    }

    //    public void show(View v) {
//        switch (v.getId()) {
//            case R.id.btn_event:
//                ToastUtil.showMsg(EventActivity.this, "布局文件的onClick属性(针对点击事件),Click...");
//                break;
//        }
//    }
}
