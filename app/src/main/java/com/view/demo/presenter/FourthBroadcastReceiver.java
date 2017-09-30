package com.view.demo.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 *
 * Created by Mark on 2017/9/30.
 */

public class FourthBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("CDH", "FourthBroadcastReceiver msg:"+intent.getStringExtra("msg"));
    }
}
