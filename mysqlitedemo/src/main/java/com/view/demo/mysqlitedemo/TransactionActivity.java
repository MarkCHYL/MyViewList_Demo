package com.view.demo.mysqlitedemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.view.demo.mysqlitedemo.utils.Constant;
import com.view.demo.mysqlitedemo.utils.DbManager;
import com.view.demo.mysqlitedemo.utils.MySqliteHelper;

/**
 * btn_transation  数据库的事物操作
 * Created by Mark on 2017/10/30.
 */

public class TransactionActivity extends AppCompatActivity {

    private MySqliteHelper helper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_transation);
//        helper = new MySqliteHelper(TransactionActivity.this);
        helper = DbManager.getInstance(TransactionActivity.this);
    }

    /**
     * 点击批量插入数据
     */
    public void insertData(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        //1.数据库的显示开启事物
        db.beginTransaction();
        for (int i = 1; i <= 100; i++) {
            String sql = "insert into " + Constant.TABLE_NAME + " values("+ i+ ",'mark"+ i +"',20)";
            db.execSQL(sql);
        }
        //2.提交当前事物
        db.setTransactionSuccessful();
        //3.关闭事物
        db.endTransaction();
        db.close();
    }
}
