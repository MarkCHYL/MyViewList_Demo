package com.view.demo.mysqlitedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.view.demo.mysqlitedemo.R;
import com.view.demo.mysqlitedemo.bean.Person;

import java.util.List;

/**
 * Created by Mark on 2017/10/31.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Person> mList;

    public MyAdapter(Context context, List<Person> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.layout_item_person,null,false);
            holder = new ViewHolder();
            holder.tv_id = (TextView)convertView.findViewById(R.id.tv_id);
            holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            holder.tv_age = (TextView)convertView.findViewById(R.id.tv_age);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_id.setText(mList.get(i).get_id() + "");
        holder.tv_name.setText(mList.get(i).getName());
        holder.tv_age.setText(mList.get(i).getAge() + "");
        return convertView;
    }

    static class ViewHolder{
        TextView tv_id,tv_name,tv_age;
    }
}
