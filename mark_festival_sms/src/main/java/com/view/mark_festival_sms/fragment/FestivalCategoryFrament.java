package com.view.mark_festival_sms.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.view.mark_festival_sms.ChooseMsgActivity;
import com.view.mark_festival_sms.R;
import com.view.mark_festival_sms.bean.FestivalBean;
import com.view.mark_festival_sms.bean.FestivalLab;

/**
 *
 * Created by Mark on 2017/10/13.
 */

public class FestivalCategoryFrament extends Fragment {

    private GridView gridView;
    private ArrayAdapter<FestivalBean> arrayAdapter;
    private LayoutInflater mInflater;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_festival_category,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(getActivity());
        gridView =(GridView)view.findViewById(R.id.gd_festivsl_show);
        gridView.setAdapter(arrayAdapter = new ArrayAdapter<FestivalBean>(getActivity(),-1, FestivalLab.getmInstance().getmFestivals()){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null){
                    convertView = mInflater.inflate(R.layout.item_festival,parent,false);
                }
                TextView tv = (TextView)convertView.findViewById(R.id.tv_festival_name);
                tv.setText(getItem(position).getName());
                return convertView;
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ChooseMsgActivity.class);
                intent.putExtra("festival_id",arrayAdapter.getItem(i).getId());
                startActivity(intent);
            }
        });
    }
}
