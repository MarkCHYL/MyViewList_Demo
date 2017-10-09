package com.view.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 本地缓存
 * Created by mark on 2017/10/9.
 */

public class PreferenceUtil {
    public static String getGesturePassword(Context context){
        SharedPreferences sp = context.getSharedPreferences("gesturePassword",Context.MODE_PRIVATE);
        String mPassword = sp.getString("password","");
        return mPassword;
    }

    public static void setGesturePassword(Context context,String gesturePassword){
        SharedPreferences sp = context.getSharedPreferences("gesturePassword",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("password",gesturePassword);
        editor.apply();
    }
}
