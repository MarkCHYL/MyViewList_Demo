package com.view.demo.mysqlitedemo.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 1.提供了onCreate() onUpgrade()等创建数据库更新数据库的方法
 * 2.提供了获取数据库对象的函数
 * Created by Mark on 2017/10/26.
 */
public class MySqliteHelper extends SQLiteOpenHelper{

    /**
     * 构造函数
     * @param context  上下文对象
     * @param name     表示创建数据库的名称
     * @param factory  游标工厂
     * @param version  表示创建数据库的版本 >= 1
     */
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /**
     * 自定义的数据库帮助类的构造函数
     * 方便调用管理
     * @param context
     */
    public MySqliteHelper(Context context){
        super(context,Constant.DATABASE_NAME,null,Constant.DATABASE_VERSION);
    }
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /**
     * 当数据库创建时回调的函数
     * @param sqLiteDatabase 数据库对象
     * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("tag","--------onCreate-----------");
        String sql = "create table " + Constant.TABLE_NAME + "("
                +Constant._ID + " Integer primary key,"
                + Constant.NAME + " varchar(10),"+
                Constant.AGE+ " Integer)";
        sqLiteDatabase.execSQL(sql);
    }

    /**
    * 当数据库版本更新时的回调函数
    * @param i 数据库旧版本
     * @param i1 数据库新版本
     * @param sqLiteDatabase 数据库对象
    * */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("tag","--------onUpgrade-----------");
    }

    /**
     * 当数据库打开时回调的函数
     * @param db 数据库的对象
     * */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.i("tag","--------onOpen-----------");
    }
}
