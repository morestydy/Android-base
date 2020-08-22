package com.example.helloworld.web;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebApiActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int GETDATA_SUCCESS = 101;
    private Button mBtnSwap;
    private TextView mTvShow;
    private InputStream inputStream;
    private BufferedReader bufferedReader;
    private HttpURLConnection connection;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what==GETDATA_SUCCESS){
                String data = msg.getData().getString("data");
                Log.d("data",data);
                mTvShow.setText(data);
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_api);

        initUI();

        initData();
    }

    private void initUI() {
        mBtnSwap = findViewById(R.id.btn_swap);
        mTvShow = findViewById(R.id.tv_show);
        mBtnSwap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_swap){
            initData();
        }
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = getDataFromServer();
                Log.d("MainActivity:","获取数据"+data);

                Message message = Message.obtain();
                Bundle bundle=new Bundle();
                bundle.putString("data",data);
                message.setData(bundle);
                message.what = GETDATA_SUCCESS;
                handler.sendMessage(message);
            }
        }).start();
    }
    //从服务器获取数据
    private String getDataFromServer() {
        try {
            //1.创建一个UI
            URL url = new URL("https://www.baidu.com");
            //2.打开连接
            connection = (HttpURLConnection) url.openConnection();
            //3.判断处理结果
            if (connection.getResponseCode()==200){
                //获取输入流
                inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                for (String line ="";(line= bufferedReader.readLine())!=null;){
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream!=null)inputStream.close();
                if (bufferedReader!=null)bufferedReader.close();
                if (connection!=null)connection.disconnect();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }
}