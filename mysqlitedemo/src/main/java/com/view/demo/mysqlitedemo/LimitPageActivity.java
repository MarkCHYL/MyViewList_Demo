package com.view.demo.mysqlitedemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.view.demo.mysqlitedemo.adapter.MyAdapter;
import com.view.demo.mysqlitedemo.bean.Person;
import com.view.demo.mysqlitedemo.utils.Constant;
import com.view.demo.mysqlitedemo.utils.DbManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现数据库分页查询
 * Created by Mark on 2017/10/31.
 */

public class LimitPageActivity extends AppCompatActivity {

    private ListView listView;
    private SQLiteDatabase db;
    //1.获取数据库查询的数据源
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator +"info.db";
    private int totalNum;//数据加载的总条数
    private int pageSize = 10;//每页展示数据的条目
    private int pageNum ;//表示总页码
    private int currentPage = 1;//当前的页码
    private List<Person> list = new ArrayList<>();
    private MyAdapter adapter;
    private boolean isDivPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit_page);

        initViews();
    }

    private void initViews() {
        listView = (ListView) findViewById(R.id.lv_limit_page);
        db = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
         //获取数据库的总条目
        totalNum = DbManager.getDataCount(db, Constant.TABLE_NAME);
        //根据总条目的大小和每页展示的数据条目   获取总页数
        pageNum = (int) Math.ceil(totalNum/(double)pageSize);
        if (currentPage == 1){
            list = DbManager.getListByCurrentPage(db,Constant.TABLE_NAME,
                    currentPage,pageSize);
        }
        adapter = new MyAdapter(LimitPageActivity.this,list);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (isDivPage && AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        == scrollState){
                    if (currentPage<pageNum){
                        currentPage++;
                        list.addAll(DbManager.getListByCurrentPage(db,Constant.TABLE_NAME,
                                currentPage,pageSize));
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                Log.e("TTTT",firstVisibleItem + "-----"+visibleItemCount+ "-----"+totalItemCount+ "-----"+currentPage);
                isDivPage = ((firstVisibleItem + visibleItemCount) == totalItemCount);
            }
        });
    }
}
