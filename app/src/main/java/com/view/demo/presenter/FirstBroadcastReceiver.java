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

public class FirstBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("CDH", "FirstBroadcastReceiver msg:"+intent.getStringExtra("msg"));
        Bundle mBundle = this.getResultExtras(true);//true：前一个广播没有结果时创建新的Bundle；false：不创建Bundle
        if (mBundle == null) {
            Log.i("CDH", "没有获取到前一个运行的广播返回的数据");
        } else {
            Log.i("CDH", "前一个运行的广播返回的数据为：" + mBundle.getString("msg"));
        }
        abortBroadcast();//终止广播，后面的广播接收器不会得到执行。
    }
}
