package com.example.helloworld.jump;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;
import com.example.helloworld.util.ToastUtil;

/**
 * com.example.helloworld.jump
 * HelloWorld
 *
 * @author:Tom 2020/7/27
 * 描述:
 **/

public class AActivity extends AppCompatActivity {

    private Button mBtnJump,mBtnjump2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Log.d("AActivity","---onCreate---");
        Log.d("AActivity","taskid:"+getTaskId()+",hashcode:"+hashCode());
        logTaskName();
        mBtnJump = findViewById(R.id.jump);
        mBtnjump2=findViewById(R.id.jump_2);
        mBtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示跳转1  常用
                Intent intent = new Intent(AActivity.this,BActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name","Tom");
                bundle.putInt("num",880);
                intent.putExtras(bundle);
                startActivity(intent);
//                startActivityForResult(intent,0);

////                //显示跳转2
//                Intent intent = new Intent();
//                intent.setClass(AActivity.this,BActivity.class);
//                startActivity(intent);

//                //显示跳转3
//                Intent intent = new Intent();
//                intent.setClassName(AActivity.this,"com.example.helloworld.jump.BActivity");
//                startActivity(intent);
                //显示跳转4
//                Intent intent = new Intent();
//                intent.setComponent(new ComponentName(AActivity.this,"com.example.helloworld.jump.BActivity"));
//                startActivity(intent);

//                //隐式跳转
//                Intent intent = new Intent();
//                intent.setAction("com.example.helloworld.jump.BActivity");
//                startActivity(intent);
            }
        });
        mBtnjump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AActivity.this, AActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ToastUtil.showMsg(AActivity.this,data.getStringExtra("title"));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("AActivity","---onCreate---");
        Log.d("AActivity","taskid:"+getTaskId()+",hashcode:"+hashCode());
        logTaskName();
    }

    private void logTaskName(){
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            Log.d("AActivity",info.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
