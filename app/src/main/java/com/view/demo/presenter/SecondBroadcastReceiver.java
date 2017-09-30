package com.view.demo.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 *
 * Created by Mark on 2017/9/30.
 */

public class SecondBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("CDH", "SecondBroadcastReceiver msg:"+intent.getStringExtra("msg"));
        Bundle bundle = new Bundle();
        bundle.putString("msg", "SecondBroadcastReceiver+++++++++++");
        setResultExtras(bundle);
    }
}
