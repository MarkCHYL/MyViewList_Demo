package com.view.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.view.demo.R;
import com.view.demo.utils.PreferenceUtil;

/**
 * 欢迎界面
 * Created by mark on 2017/10/9.
 */

public class WelcomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String passwordStr = PreferenceUtil.getGesturePassword(WelcomeActivity.this);
                Intent intent;
                if (passwordStr == ""){
                    Log.d("TAG","true");
                    intent = new Intent(WelcomeActivity.this,SetLockActivity.class);
                } else {
                    intent = new Intent(WelcomeActivity.this,UnlockActivity.class);
                }
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        },2000);
    }
}
