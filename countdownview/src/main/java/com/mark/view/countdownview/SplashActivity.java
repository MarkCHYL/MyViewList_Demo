package com.mark.view.countdownview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 *
 * Created by Administrator on 2017/12/4.
 */

public class SplashActivity extends AppCompatActivity {

    private CountDownView countdownview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplash);
        countdownview = (CountDownView)findViewById(R.id.countdownview);
        countdownview.setOnLoadingFinishListener(new CountDownView.OnLoadingFinishListener() {
            @Override
            public void finish() {
                startActivity(new Intent(SplashActivity.this,MarkActivity.class));
            }
        });
        countdownview.start();
    }
}
