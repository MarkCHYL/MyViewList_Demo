package com.view.demo.ui;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.view.demo.R;

import java.util.List;

/**
 * 蓝牙
 * Created by Mark on 2017/10/13.
 */

public class BlueToothActivity extends AppCompatActivity {

    private static final String TAG = "Mark";
    private static final int REQUEST_OPEN_BT = 11082;
    private Button btn_bluetooth;
    private BluetoothAdapter bAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bluetooth);

        initView();
    }

    private void initView() {
        btn_bluetooth = (Button) findViewById(R.id.btn_bluetooth);
        //获取本地的蓝牙适配器
        bAdapter = BluetoothAdapter.getDefaultAdapter();
        //判断蓝牙功能是否存在
        if (bAdapter == null){
            showToast("该设备不支持蓝牙....");
            return;
        }
        //获取蓝牙的名字  mac地址
        String name = bAdapter.getName();
        String mac = bAdapter.getAddress().toString();
        Log.e(TAG,"名字：" + name + ",Mac地址：" + mac);
        //获取当前蓝牙的状态
        int state = bAdapter.getState();
        switch (state){
            case BluetoothAdapter.STATE_ON://蓝牙已经打开
                showToast("蓝牙已打开");
                break;
            case BluetoothAdapter.STATE_TURNING_ON://蓝牙正在发开
                showToast("蓝牙正在打开");
                break;
            case BluetoothAdapter.STATE_OFF://蓝牙已关闭
                showToast("蓝牙已关闭");
                break;
            case BluetoothAdapter.STATE_TURNING_OFF://蓝牙正在关闭
                showToast("蓝牙正在关闭");
                break;

        }

        btn_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bAdapter.isEnabled()){
                    showToast("蓝牙已经处于打开状态.....");
                    //关闭蓝牙
                    boolean isClose = bAdapter.disable();
                    Log.e(TAG,"蓝牙是否关闭：" + isClose);
                }else {
                    //打开蓝牙
                    boolean isOpen = bAdapter.enable();

                    //调用系统API打开蓝牙
                   // Intent open = new Intent(bAdapter.ACTION_REQUEST_ENABLE);
//                    startActivityForResult(open,REQUEST_OPEN_BT);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_OPEN_BT == requestCode){
            if (resultCode == RESULT_CANCELED){
                showToast("请求失败");
            }else {
                showToast("请求成功。。。。");
            }
        }
    }

    public void showToast(String str){
        Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
