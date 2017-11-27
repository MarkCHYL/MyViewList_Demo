package com.mark.demo.lockscreenmsgdemo.service;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mark.demo.lockscreenmsgdemo.ui.MessageActivity;

/**
 * Created by Mark on 2017/11/24.
 */

public class LockScreenMsgReceiver extends BroadcastReceiver {
    private static final String TAG = "LockScreenMsgReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive:收到了锁屏消息 ");
        String action = intent.getAction();
        if (action.equals("com.mark.demo.lockscreenmsgdemo.service.LockScreenMsgReceiver")){
            //管理锁屏的一个服务
            KeyguardManager km = (KeyguardManager)
                    context.getSystemService(Context.KEYGUARD_SERVICE);
            String text = km.inKeyguardRestrictedInputMode() ? "锁屏啦" : "屏幕亮着呢";
            Log.i(TAG,"text: " + text);
            if (km.inKeyguardRestrictedInputMode()){
                Log.i(TAG,"onService:锁屏啦");
                //判断是否锁屏
                Intent alarmIntent = new Intent(context, MessageActivity.class);
                //在广播中启动Activity的context可能不是Activity对象，
                // 所以需要添加NEW_TASK的标志，否则启动时可能会报错。
                alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(alarmIntent);//启动显示锁屏消息的activity
            }
        }

    }
}