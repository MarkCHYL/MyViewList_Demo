package com.view.demo.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 *
 * Created by Mark on 2017/9/30.
 */

public class ThirdBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("CDH", "ThirdBroadcastReceiver msg:"+intent.getStringExtra("msg"));
    }
}
