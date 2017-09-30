package com.view.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.view.demo.R;

/**
 * doBroadcasts  广播的学习
 * Created by Mark on 2017/9/30.
 */

public class BroadcastsDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_broadcast);
    }

    public void doOrdered(View view){
        //在前一个执行的广播中设置结果值，传给下一个广播。也可以通过BroadcastReceiver.abortBroadcast()终止广播，后面的广播接收器不会得到执行。
        //发送广播
        /*
        * 通过android:priority="N"设置广播执行顺序(N的取值-1000到1000)，优先级高的广播先执行。
        * （这里先执行SecondBroadcastReceiver再执行FirstBroadcastReceiver）
        * 在前一个执行的广播中设置结果值，传给下一个广播。也可以通过BroadcastReceiver.abortBroadcast()终止广播，后面的广播接收器不会得到执行。
        * */
        Intent intent = new Intent("com.improve.broadcast");
        intent.putExtra("msg", "BroadcastReceiverDemo");
        sendOrderedBroadcast(intent, null);
    }

    public void doNormal(View view){
       /* //发送广播
        *//*
        * 无序广播虽说是没有顺序随机的，但是大多数情况下是按在清单文件中的注册顺序来的
        * */
        Intent intent = new Intent("com.improve.broadcast");
        intent.putExtra("msg","I am cool man , BroadcastReceiver Activity");
        sendBroadcast(intent);
    }
}
