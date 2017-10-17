package com.view.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.view.demo.R;
import com.view.demo.fragment.IOSDialogFragment;

/**
 * Created by Mark on 2017/10/17.
 */

public class IosDialogActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ios_dialog);
    }

    public void doIosDialogShow(View view){
        IOSDialogFragment dialogFragment = new IOSDialogFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        dialogFragment.show(ft,"df");
    }
}
