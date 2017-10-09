package com.view.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.view.demo.MarkActivity;
import com.view.demo.R;
import com.view.demo.utils.PreferenceUtil;
import com.view.demo.view.LockPatternView;

/**
 * 设置九宫格密码
 * Created by mark on 2017/10/9.
 */

public class SetLockActivity extends AppCompatActivity {

    private TextView mTextView;
    private LockPatternView mLockPatternView;
    private Button mClearBtn;

    private String mPassword;
    //是否第一次输入密码
    private boolean isFirst = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_lock);

        initView();
        initEvent();
    }

    private void initEvent() {
        mLockPatternView.setPatterChangeListener(new LockPatternView.OnPatterChangeListener() {
            @Override
            public void onPatterChange(String passwordStr) {
                if (!TextUtils.isEmpty(passwordStr)){
                    if (isFirst) {
                        mPassword = passwordStr;
                        mTextView.setText("再次输入手势密码");
                        isFirst = false;
                        mClearBtn.setVisibility(View.VISIBLE);
                    } else {
                        if (passwordStr.equals(mPassword)) {
                            setPasswordToPreference(passwordStr);
                            startActivity(new Intent(SetLockActivity.this, MarkActivity.class));
                            SetLockActivity.this.finish();
                        }else {
                            Toast.makeText(SetLockActivity.this,"两次密码不一致，请重新设置",Toast.LENGTH_SHORT).show();
                            mTextView.setText("设置手势密码");
                            isFirst = false;
                            mClearBtn.setVisibility(View.GONE);
                        }
                    }
                } else {
                    mTextView.setText("至少5个图案");
                }
            }

            @Override
            public void onPatterStart(boolean isStart) {
                if (isStart){
                    mTextView.setText("请绘制图案");
                }
            }
        });

        mClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPassword = "";
                isFirst = true;
                mClearBtn.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onBackPressed() {
    }

    private void setPasswordToPreference(String password){
        PreferenceUtil.setGesturePassword(SetLockActivity.this,password);
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.tv_activity_set_lock_title);
        mLockPatternView = (LockPatternView) findViewById(R.id.activity_gesture_lock);
        mClearBtn = (Button) findViewById(R.id.btn_password_clear);
    }
}
