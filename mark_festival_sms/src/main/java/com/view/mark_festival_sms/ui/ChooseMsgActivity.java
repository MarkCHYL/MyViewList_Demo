package com.view.mark_festival_sms.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.view.mark_festival_sms.R;
import com.view.mark_festival_sms.bean.FestivalLab;
import com.view.mark_festival_sms.bean.Msg;


/**
 * 短信列表
 * Created by Mark on 2017/10/16.
 */

public class ChooseMsgActivity extends AppCompatActivity{
    private ListView mLvMsgs;
    private FloatingActionButton mFabToSend;
    private ArrayAdapter<Msg> mAdapter;
    private int mFestivalId;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_msg_choose);

        mFestivalId = getIntent().getIntExtra("festival_id",-1);
        setTitle(FestivalLab.getmInstance().getmFestivalById(mFestivalId).getName());
        mInflater = LayoutInflater.from(this);

        initView();
        initEvent();
    }

    private void initEvent() {
        mFabToSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SendMsgactivity.toActivity(ChooseMsgActivity.this,mFestivalId,-1);
            }
        });
    }

    private void initView() {
        mLvMsgs = (ListView) findViewById(R.id.id_lv_msgs);
        mFabToSend = (FloatingActionButton) findViewById(R.id.id_fab_toSend);

        mLvMsgs.setAdapter(mAdapter =
                new ArrayAdapter<Msg>(this,-1, FestivalLab
                        .getmInstance().getMsgByFestivalId(mFestivalId)){
                    @NonNull
                    @Override
                    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if (convertView == null){
                            convertView = mInflater.inflate(R.layout.item_msg,parent,false);
                        }
                        TextView content = (TextView)convertView.findViewById(R.id.id_tv_content);
                        Button sendBtn = (Button)convertView.findViewById(R.id.id_btn_toSend);
                        content.setText("     " + getItem(position).getContent());

                        sendBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SendMsgactivity.toActivity(ChooseMsgActivity.this,mFestivalId,getItem(position).getId());
                            }
                        });
                        return convertView;
                    }
                });
    }
}
