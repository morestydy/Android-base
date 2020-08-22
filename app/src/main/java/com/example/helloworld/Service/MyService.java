package com.example.helloworld.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private DownloadBinder mbinder = new DownloadBinder();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mbinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //服务创建时调用
        Log.d("Myservice","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //启动服务时调用
        Log.d("Myservice","onStartCommand"+startId);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // TODO: 2020/8/22  处理相关的逻辑
//                //须使用stopSelf()或stopService();才能停止
//                stopSelf();
//            }
//        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //服务销毁时
        Log.d("Myservice","onDestroy");
    }

    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("Myservice","startDownload");
        }

        public int getProgress(){
            Log.d("Myservice","getProgress");
            return 0;
        }


    }
}
