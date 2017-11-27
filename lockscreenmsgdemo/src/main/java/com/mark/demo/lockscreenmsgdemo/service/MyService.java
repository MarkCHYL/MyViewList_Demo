package com.mark.demo.lockscreenmsgdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Mark on 2017/11/24.
 */

public class MyService extends Service {
    private static  final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        Log.i(TAG,"onStartCommand:");
        sendMessage();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy:");
        super.onDestroy();
    }

    /**
     * 模仿推送，发消息
     */
    private void sendMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.setAction("com.mark.demo.lockscreenmsgdemo.service.LockScreenMsgReceiver");
                sendBroadcast(intent);//发送广播
            }
        }).start();
    }
}
