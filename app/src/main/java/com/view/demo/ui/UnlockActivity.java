package com.view.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.view.demo.MarkActivity;
import com.view.demo.R;
import com.view.demo.utils.PreferenceUtil;
import com.view.demo.view.LockPatternView;

public class UnlockActivity extends AppCompatActivity {

    private LockPatternView mLockPatternView;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        mLockPatternView = (LockPatternView) findViewById(R.id.activity_gesture_lock);
        mTitle = (TextView) findViewById(R.id.tv_activity_set_lock_title);

        mLockPatternView.setPatterChangeListener(new LockPatternView.OnPatterChangeListener() {
            @Override
            public void onPatterChange(String passwordStr) {
                String password = PreferenceUtil.getGesturePassword(UnlockActivity.this);
                if (!TextUtils.isEmpty(passwordStr)){
                    if (passwordStr.equals(password)) {
                        Toast.makeText(UnlockActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UnlockActivity.this, MarkActivity.class);
                        startActivity(intent);
                        UnlockActivity.this.finish();
//                    return true;
                    } else {
                        Toast.makeText(UnlockActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    mTitle.setText("至少5个图案");
                }
            }

            @Override
            public void onPatterStart(boolean isStart) {
                if (isStart){
                    mTitle.setText("请绘制图案");
                }
            }
        });


    }

}
