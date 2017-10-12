package com.view.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.view.demo.R;

/**
 *
 * Created by mark on 2017/10/11.
 */

public class DoActivityResultActivity01 extends AppCompatActivity{

    private TextView tv_show;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_result01);

        initView();
    }

    private void initView() {
        tv_show = (TextView) findViewById(R.id.tv_show);
    }

    public void doNextActivity2(View view){
        DoActivityResultActivity01.this.setResult(2);
        DoActivityResultActivity01.this.finish();
    }

    public void doNextActivity(View view){
        DoActivityResultActivity01.this.setResult(3);
        DoActivityResultActivity01.this.finish();
    }
}
