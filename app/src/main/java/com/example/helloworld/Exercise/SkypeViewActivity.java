package com.example.helloworld.Exercise;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

/**
 * com.example.helloworld.Exercise
 * HelloWorld
 *
 * @author:Tom 2020/8/20
 * 描述:
 **/

public class SkypeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skype_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }

}
