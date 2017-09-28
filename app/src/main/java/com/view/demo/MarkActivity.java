package com.view.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.view.demo.ui.EmojiActivity;
import com.view.demo.ui.SimpleCircleImageActivity;
import com.view.demo.ui.YMenueAtivity;

public class MarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
    }

    public void doCicleImg(View view){
        startActivity(new Intent(this,SimpleCircleImageActivity.class));
    }

    public void doSpanText(View view){

    }

    public void doYMunue(View view){
        startActivity(new Intent(this,YMenueAtivity.class));
    }

    public void doEmojiview(View view){
        startActivity(new Intent(this, EmojiActivity.class));
    }
}
