package com.view.demo.mysqlitedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.view.demo.mysqlitedemo.utils.Constant;

import java.io.File;

/**
 * CursorAdapter的演示
 * Created by Mark on 2017/10/30.
 */

public class CursorAdapterActivity extends AppCompatActivity {

    private ListView listView;
    private SQLiteDatabase db;
    private MyCursorAdapter cursorAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cursoradapter);

        initViews();
        setContent();
    }

    private void setContent() {
        listView.setAdapter(cursorAdapter);
        db.close();
    }

    private void initViews() {
        listView = (ListView) findViewById(R.id.lv_adapter);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator +"info.db";
        db = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("select * from " + Constant.TABLE_NAME,null);

        cursorAdapter = new MyCursorAdapter(CursorAdapterActivity.this
                ,cursor,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    /**
     * 自定义CursorAdapter适配器
     */
    public class MyCursorAdapter extends CursorAdapter{

        public MyCursorAdapter(Context context, Cursor cursor, int flags) {
            super(context, cursor, flags);
        }

        /**
         * 表创建中每个item创建对应的View视图对象
         * @param context  上下文
         * @param cursor  数据源Cursor对象
         * @param viewGroup  item对应的view的父布局
         * @return 返回的是每个item对应的View对象
         */
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View view = LayoutInflater.from(CursorAdapterActivity.this).inflate(R.layout.layout_item_person,null);
            return view;
        }

        /**
         * 通过newView展示每个item的展示的view对象，在bidView中对View中的每个空间进行填充
         * @param view 是newView中返回的每个item的view对象
         * @param context 上下文
         * @param cursor 数据源cursor对象
         */
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tv_id = (TextView) view.findViewById(R.id.tv_id);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_age = (TextView) view.findViewById(R.id.tv_age);

            int _id = cursor.getInt(cursor.getColumnIndex(Constant._ID));
            String name = cursor.getString(cursor.getColumnIndex(Constant.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(Constant.AGE));
            Log.e("TTTT",_id +"----"+ name +"----"+ age);

            tv_id.setText(cursor.getInt(cursor.getColumnIndex(Constant._ID)) + "");
            tv_name.setText(cursor.getString(cursor.getColumnIndex(Constant.NAME)));
            tv_age.setText(cursor.getInt(cursor.getColumnIndex(Constant.AGE))+"");
        }
    }
}
