package com.view.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.view.demo.R;

/**
 * StartActivityResult的使用
 * Created by mark on 2017/10/10.
 */

public class DoActivityResultActivity extends AppCompatActivity{

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

    public void doNextActivity(View view){
        startActivityForResult(new Intent(DoActivityResultActivity.this,DoActivityResultActivity01.class
                ),2);
    }
    public void doNextActivity2(View view){
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 2:
                if (resultCode == 3){
                    tv_show.setText("我想找个女朋友");
                }else if (resultCode == 2){
                    tv_show.setText("好喜欢唐嫣");
                }
                break;
        }

    }
}
