package com.example.helloworld.denglu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;
import com.example.helloworld.denglu.adapter.UserListAdapter;
import com.example.helloworld.denglu.db.dao.UserDao;
import com.example.helloworld.denglu.db.domain.User;
import com.example.helloworld.util.ToastUtil;

import java.util.List;

/**
 * com.example.helloworld.denglu
 * HelloWorld
 *
 * @author:Tom 2020/8/20
 * 描述:https://www.bilibili.com/video/BV19k4y1z74U
 **/

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mUserList;
    private Button mBtnAddinfo;
    private AlertDialog alertDialog;
    private View view;
    private EditText mEtName;
    private EditText mEtPsw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();

        initData();
    }

    private void initData() {
        UserDao userDao = UserDao.getInstance(getApplicationContext());
        List<User> userList = userDao.findall();
        UserListAdapter adapter = new UserListAdapter(getApplicationContext(), userList);
        mUserList.setAdapter(adapter);
    }

    private void initUI() {
        mUserList = findViewById(R.id.lv_list);
        mBtnAddinfo=findViewById(R.id.btn_add_info);
        mBtnAddinfo.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_info:
                showAlertDialog();
                break;
            default:
                break;

        }
    }

    private void save2db(String name, String psw) {
        UserDao userDao = UserDao.getInstance(getApplicationContext());
        User user = new User(name, psw);
        userDao.insert(user);

        initData();

    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alertDialog = builder.create();
        view = View.inflate(getApplicationContext(), R.layout.layout_dialog_userinfo,null);
        alertDialog.setView(view);
        alertDialog.show();

        mEtName = view.findViewById(R.id.et_user_name);
        mEtPsw = view.findViewById(R.id.et_psw);


        //弹框的点击事件
        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEtName.getText().toString().trim();
                String psw = mEtPsw.getText().toString().trim();

                if (name.length()==0 || psw.length() == 0){
                    ToastUtil.showMsg(LoginActivity.this,"密码或用户名无");
                }
                if (name.length()>20 || psw.length()>20){
                    ToastUtil.showMsg(LoginActivity.this,"密码或用户名过长");
                }

                save2db(name,psw);
            }
        });
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
