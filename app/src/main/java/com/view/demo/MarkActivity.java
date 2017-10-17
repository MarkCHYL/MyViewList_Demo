package com.view.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.view.demo.ui.BlueToothActivity;
import com.view.demo.ui.BroadcastsDemoActivity;
import com.view.demo.ui.DoActivityResultActivity;
import com.view.demo.ui.EmojiActivity;
import com.view.demo.ui.GestrueActivity;
import com.view.demo.ui.IosDialogActivity;
import com.view.demo.ui.SimpleCircleImageActivity;
import com.view.demo.ui.Wheel3DActivity;
import com.view.demo.ui.YMenueAtivity;

public class MarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
    }

    public void doCicleImg(View view) {
        startActivity(new Intent(this, SimpleCircleImageActivity.class));
    }

    public void doSpanText(View view) {

    }

    public void doYMunue(View view) {
        startActivity(new Intent(this, YMenueAtivity.class));
    }

    public void doEmojiview(View view) {
        startActivity(new Intent(this, EmojiActivity.class));
    }

    public void do3DWheel(View view) {
        startActivity(new Intent(this, Wheel3DActivity.class));
    }

    public void doBroadcasts(View view) {
        startActivity(new Intent(this, BroadcastsDemoActivity.class));
    }

    public void doLockIndicator(View view) {
        startActivity(new Intent(this, GestrueActivity.class));
    }

    public void doRsultActivity(View view){
        startActivity(new Intent(MarkActivity.this, DoActivityResultActivity.class));
    }

    public void doBluetooth(View view){
        startActivity(new Intent(MarkActivity.this, BlueToothActivity.class));
    }

    public void doIosDialog(View view){
        startActivity(new Intent(MarkActivity.this, IosDialogActivity.class));
    }
}
