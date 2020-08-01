package com.example.helloworld;

import android.app.Activity;
import android.view.View;

import com.example.helloworld.util.ToastUtil;

/**
 * com.example.helloworld
 * HelloWorld
 *
 * @author:Tom 2020/8/1
 * 描述:
 **/

public class MyClickListener implements View.OnClickListener {
    private Activity mactivity;
    public MyClickListener(Activity activity) {
        this.mactivity=activity;
    }

    @Override
    public void onClick(View v) {
        ToastUtil.showMsg(mactivity,"Click...");
    }
}
