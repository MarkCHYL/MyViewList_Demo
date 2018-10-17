package com.view.mark_festival_sms.ui;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.view.mark_festival_sms.R;
import com.view.mark_festival_sms.bean.FestivalBean;
import com.view.mark_festival_sms.bean.FestivalLab;
import com.view.mark_festival_sms.bean.Msg;
import com.view.mark_festival_sms.view.FlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 发送短信
 * Created by Mark on 2017/10/16.
 */

public class SendMsgactivity extends AppCompatActivity {

    private int mFestivalId;
    private int mMsgId;

    private EditText mEdMsg;
    private Button mBtnAdd;
    private FlowLayout mFlContacts;
    private FloatingActionButton mFabSend;
    private View mLayoutLoading;
    
    private FestivalBean festivalBean;
    private Msg msg;
    private EditText telenum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);
        initDatas();
        initViews();
    }

    private void initViews() {
        telenum =(EditText) findViewById(R.id.id_et_telenum);
        mEdMsg = (EditText) findViewById(R.id.id_et_content);
        mBtnAdd = (Button) findViewById(R.id.id_btn_add);
        mFlContacts = (FlowLayout) findViewById(R.id.id_fl_contacts);
        mFabSend = (FloatingActionButton) findViewById(R.id.id_fab_send);
        mLayoutLoading = findViewById(R.id.id_layout_loading);

        if (mMsgId != -1){
            msg = FestivalLab.getmInstance().getMsgByMsgId(mMsgId);
            mEdMsg.setText(msg.getContent());
            mFabSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mEdMsg.getText().toString() != null && mEdMsg.getText().toString().length() != 0){
                        // 判断环境兼容，检查自己的权限，是否被同意
                        if (ContextCompat.checkSelfPermission(SendMsgactivity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                            //如果不同意，就去请求权限   参数1：上下文，2：权限，3：请求码
                            ActivityCompat.requestPermissions(SendMsgactivity.this,new String []{Manifest.permission.SEND_SMS},1);
                        }else {
                            //同意就拨打
                            if (telenum.getText() != null && telenum.getText().length() == 11){
                                sendSMS(telenum.getText().toString(),msg.getContent());
                            }

                        }
                    }
                }
            });
        }else {
            mFabSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mEdMsg.getText().toString() != null && mEdMsg.getText().toString().length() != 0){
                        // 判断环境兼容，检查自己的权限，是否被同意
                        if (ContextCompat.checkSelfPermission(SendMsgactivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                            //如果不同意，就去请求权限   参数1：上下文，2：权限，3：请求码
                            ActivityCompat.requestPermissions(SendMsgactivity.this,new String []{Manifest.permission.SEND_SMS},1);
                        }else {
                            //同意就拨打
                            if (telenum.getText() != null && telenum.getText().length() == 11){
                                sendSMS(telenum.getText().toString(),mEdMsg.getText().toString());
                            }

                        }
                    }
                }
            });
        }

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(SendMsgactivity.this,
                        Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                    //如果不同意，就去请求权限   参数1：上下文，2：权限，3：请求码
                    ActivityCompat.requestPermissions(SendMsgactivity.this,
                            new String []{Manifest.permission.READ_CONTACTS},1);
                }else {
                    startActivityForResult(new Intent(SendMsgactivity.this,
                            ContactsActivity.class),2121);
                }
            }
        });


    }

    private void initDatas() {
        mFestivalId = getIntent().getIntExtra("festivalId",-1);
        mMsgId = getIntent().getIntExtra("msgId",-1);

        festivalBean = FestivalLab.getmInstance().getmFestivalById(mFestivalId);
        setTitle(festivalBean.getName());
    }

    public static void toActivity(Context context,int festivalId,int msgId){
        Intent intent = new Intent(context,SendMsgactivity.class);
        intent.putExtra("festivalId",festivalId);
        intent.putExtra("msgId",msgId);
        context.startActivity(intent);
    }

    public void sendSMS(String phoneNumber,String message){
         /*获取系统默认的信息管理器，一定要注意的是SmsManager是android.telephony.SmsManager;这和
         *我们使用的版本有关，在 Android 2.0 以前 应该使用 android.telephony.gsm.SmsManager
         *Android 2.0 之后的版本应该用 android.telephony.SmsManager。
         */
        SmsManager smsManager = SmsManager.getDefault();
        /*PendingIntent.getBroadcast返回一个用于广播的PendingIntent对象，类似于调用Content.sendBroadcast();
         */
        PendingIntent paIntent = PendingIntent.getBroadcast(SendMsgactivity.this, 0, new Intent("SMS_SENT"), 0);
        PendingIntent deliveryIntent = PendingIntent.getBroadcast(SendMsgactivity.this, 0, new Intent("SMS_DELIVERED"), 0);
        // smsManager.divideMessage有些时候短信如果超过了字数，我们就需要这个方法来帮我们拆分短信内容。
        ArrayList<String> smses = smsManager.divideMessage(message);
        Iterator<String> iterator = smses.iterator();
        while(iterator.hasNext()){
            String temp = iterator.next();
            //发送短信
            smsManager.sendTextMessage(phoneNumber, null, temp, paIntent, deliveryIntent);
        }
        // 弹出一个浮动框显示提示内容，Toast.LENGTH_LONG代表浮动框显示时间的长短
        Toast.makeText(SendMsgactivity.this, "短信发送完成", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 2121:
                if (resultCode == 101){
                    Log.d("test", data.getStringExtra("phoneNum"));
                    String phoneNum = data.getStringExtra("phoneNum");
                    telenum.setText(getNum(phoneNum));
                }
                break;
        }
    }

    /**
     * 获取数字
     * @param phoneNum
     */
    private String getNum(String phoneNum) {
        String str = null;
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(phoneNum);
        str =  m.replaceAll("").trim();
        return str;
    }
}
