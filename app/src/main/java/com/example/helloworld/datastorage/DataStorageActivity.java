package com.example.helloworld.datastorage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

public class DataStorageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnsharedpreferences,mBtnFile,mBtnOutFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);

        mBtnsharedpreferences=findViewById(R.id.btn_sharedpreferences);
        mBtnsharedpreferences.setOnClickListener(this);
        mBtnFile = findViewById(R.id.btn_file);
        mBtnFile.setOnClickListener(this);

        mBtnOutFile = findViewById(R.id.btn_outfile);
        mBtnOutFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_sharedpreferences:
                intent = new Intent(DataStorageActivity.this,SharedPerferencesActivity.class);
                break;
            case R.id.btn_file:
                intent = new Intent(DataStorageActivity.this,FileActivity.class);
                break;
            case R.id.btn_outfile:
                intent = new Intent(DataStorageActivity.this,FileOutActivity.class);
                break;
        }
        startActivity(intent);
    }
}