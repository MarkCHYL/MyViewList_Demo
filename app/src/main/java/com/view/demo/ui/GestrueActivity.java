package com.view.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.view.demo.R;
import com.view.demo.view.LockPatternView;

import static com.view.demo.R.id.activity_gesture_lock_hint;

/**
 * 九宫格解锁
 * Created by Mark on 2017/10/9.
 */

public class GestrueActivity extends AppCompatActivity implements LockPatternView.OnPatterChangeListener {

    private TextView lockHint;
    private LockPatternView lockPatterView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gestrue);
        initView();
    }

    private void initView() {
        lockHint = (TextView) findViewById(activity_gesture_lock_hint);
        lockPatterView = (LockPatternView) findViewById(R.id.activity_gesture_lock);
        lockPatterView.setPatterChangeListener(this);
    }

    @Override
    public void onPatterChange(String passwordStr) {
        if (!TextUtils.isEmpty(passwordStr)){
            lockHint.setText(passwordStr.toString());
        }else {
            lockHint.setText("至少5个图案");
        }
    }

    @Override
    public void onPatterStart(boolean isStart) {
        if (isStart){
            lockHint.setText("请绘制图案");
        }
    }
}
