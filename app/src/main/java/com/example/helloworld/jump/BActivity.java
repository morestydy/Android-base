package com.example.helloworld.jump;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

/**
 * com.example.helloworld.jump
 * HelloWorld
 *
 * @author:Tom 2020/7/27
 * 描述:
 **/

public class BActivity extends AppCompatActivity {
    private TextView mTvTitle;
    private Button mBtnfinish,mBtnJump;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        mTvTitle=findViewById(R.id.tv_title);
        Log.d("AActivity","---onCreate---");
        Log.d("AActivity","taskid:"+getTaskId()+",hashcode:"+hashCode());
        logTaskName();
        final Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        int num=bundle.getInt("num");

        mTvTitle.setText(name+"name,"+num);

        mBtnfinish = findViewById(R.id.btn_finish);
        mBtnJump = findViewById(R.id.btn_2);
        mBtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BActivity.this,AActivity.class);
                startActivity(intent);
            }
        });

        mBtnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle1 = new Bundle();
                bundle.putString("title","TomF");
                intent.putExtras(bundle);
                setResult(AActivity.RESULT_OK,intent);
                finish();
            }
        });
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("AActivity","---onCreate---");
        Log.d("AActivity","taskid:"+getTaskId()+",hashcode:"+hashCode());
        logTaskName();
    }
    private void logTaskName(){
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            Log.d("BActivity",info.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
