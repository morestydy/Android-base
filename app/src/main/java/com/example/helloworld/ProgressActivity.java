package com.example.helloworld;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.util.ToastUtil;

/**
 * com.example.helloworld
 * HelloWorld
 *
 * @author:Tom 2020/7/23
 * 描述:
 **/

public class ProgressActivity extends AppCompatActivity {
    private ProgressBar mPb3;
    private Button mBtnStart,mBtnProgressDialog1,mBtnProgressDialog2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        mBtnStart=findViewById(R.id.btn_start);
        mBtnProgressDialog1=findViewById(R.id.btn_progress_dialog1);
        mBtnProgressDialog2 = findViewById(R.id.btn_progress_dialog2);
        mPb3=findViewById(R.id.pb3);
        setListeners();
    }
    protected void setListeners(){
        OnClick onClick = new OnClick();
        mBtnStart.setOnClickListener(onClick);
        mBtnProgressDialog1.setOnClickListener(onClick);
        mBtnProgressDialog2.setOnClickListener(onClick);


    }
    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_start:
                    handler.sendEmptyMessage(0);
                    break;
                case R.id.btn_progress_dialog1:
                    final ProgressDialog progressDialog1 = new ProgressDialog(ProgressActivity.this);
                    progressDialog1.setMessage("正在加载中");
                    progressDialog1.setTitle("提示");
                    progressDialog1.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            ToastUtil.showMsg(ProgressActivity.this,"cancel...");
                        }
                    });
                    progressDialog1.setCancelable(false);
                    progressDialog1.show();
//                    try {
//                        Thread.sleep(5000);
//                        progressDialog.dismiss();
//                    } catch (InterruptedException e) {
//                        return;
//                    }
                    break;
                case R.id.btn_progress_dialog2:
                    final ProgressDialog progressDialog2 = new ProgressDialog(ProgressActivity.this);
                    progressDialog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog2.setTitle("提示");
                    progressDialog2.setMessage("正在下载...");
                    progressDialog2.setCancelable(false);
                    progressDialog2.setButton(DialogInterface.BUTTON_POSITIVE, "棒", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(ProgressActivity.this,"hao");
                        }
                    });
                    progressDialog2.setButton(DialogInterface.BUTTON_NEGATIVE, "还行", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(ProgressActivity.this,"well");
                        }
                    });
                    progressDialog2.setButton(DialogInterface.BUTTON_NEUTRAL, "good", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(ProgressActivity.this,"good");
                        }
                    });
                    progressDialog2.show();
                    break;
                default:
                    break;
            }

        }
    }




    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (mPb3.getProgress() < 100){
                handler.postDelayed(runnable,500);
            }else {
                ToastUtil.showMsg(ProgressActivity.this,"加载完成");
            }
        }
    };






    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mPb3.setProgress(mPb3.getProgress()+5);
            handler.sendEmptyMessage(0);
        }
    };
}
