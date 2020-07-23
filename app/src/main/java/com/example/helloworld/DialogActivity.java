package com.example.helloworld;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.util.ToastUtil;

public class DialogActivity extends AppCompatActivity {

    private Button mBtnDialog1,mBtnDialog2,mBtnDialog3,mBtnDialog4,mBtnDialog5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        mBtnDialog1=findViewById(R.id.btn_dialog1);
        mBtnDialog2=findViewById(R.id.btn_dialog2);
        mBtnDialog3=findViewById(R.id.btn_dialog3);
        mBtnDialog4=findViewById(R.id.btn_dialog4);
        mBtnDialog5=findViewById(R.id.btn_dialog5);
        OnClick onClick = new OnClick();
        mBtnDialog1.setOnClickListener(onClick);
        mBtnDialog2.setOnClickListener(onClick);
        mBtnDialog3.setOnClickListener(onClick);
        mBtnDialog4.setOnClickListener(onClick);
        mBtnDialog5.setOnClickListener(onClick);
    }

    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_dialog1:
                    AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                    builder.setTitle("请回答")
                            .setIcon(R.drawable.icon_smile)
                            .setMessage("Are you ok?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ToastUtil.showMsg(DialogActivity.this,"good");
                                }
                            }).setNeutralButton("haixing", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,"repeat once");
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,"not good!");
                        }
                    }).show();
                    break;
                case R.id.btn_dialog2:
                    /**
                     * 弹出单选
                     */
                    final String[] array = new String[]{"男","女"};
                    AlertDialog.Builder builder1=new AlertDialog.Builder(DialogActivity.this);
                    builder1.setTitle("选择性别").setItems(array, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,array[which]);
                        }
                    }).show();
                    break;
                case R.id.btn_dialog3:
                    final String[] array3 = new String[]{"男","女"};
                    AlertDialog.Builder builder3=new AlertDialog.Builder(DialogActivity.this);
                    builder3.setTitle("选择性别").setSingleChoiceItems(array3, 1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,array3[which]);
                            dialog.dismiss();
                        }
                    }).setCancelable(false).show();
                    break;
                case R.id.btn_dialog4:
                    final String[] array4 = new String[]{"java","python","others"};
                    AlertDialog.Builder builder4= new AlertDialog.Builder(DialogActivity.this);
                    boolean[] isSelected = new boolean[]{false,false,true};
                    builder4.setTitle("选择").setMultiChoiceItems(array4, isSelected, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            ToastUtil.showMsg(DialogActivity.this,array4[which]+":"+isChecked);
                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,"not good!");
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,"repeat once");
                        }
                    }).show();
                    break;
                case R.id.btn_dialog5:
                    final AlertDialog.Builder builder5 = new AlertDialog.Builder(DialogActivity.this);
                    View view=LayoutInflater.from(DialogActivity.this).inflate(R.layout.layout_dialog,null);
                    final EditText etUserName=view.findViewById(R.id.et_username);//获取用户名
                    final EditText etPassWord = view.findViewById(R.id.et_password);//获取密码
                    Button btnLogin = view.findViewById(R.id.btn_login);//登录按钮
                    final AlertDialog dialog=builder5.setTitle("先登录").setView(view).setCancelable(false).show();
                    btnLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();//关闭页面
                            Toast.makeText(DialogActivity.this,"用户:"+etUserName.getText()+"\n密码:"+etPassWord.getText(),Toast.LENGTH_LONG).show();
                        }
                    });
                    break;
            }
        }
    }
}
