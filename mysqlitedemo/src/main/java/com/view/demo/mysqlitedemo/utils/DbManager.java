package com.view.demo.mysqlitedemo.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.view.demo.mysqlitedemo.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要是对数据操作的工具类
 * Created by Mark on 2017/10/26.
 */
public class DbManager {
    private static MySqliteHelper helper;
    public static MySqliteHelper getInstance(Context context){
        if (helper == null){
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    /**
     * 根据sql语句查询获取Cursor对象
     * @param db   数据库对象
     * @param sql  查询的sql语句
     * @param selectionArgs  查询条件的占位符
     * @return  查询的结果
     */
    public static Cursor selectDataBySql(SQLiteDatabase db,String sql,String[] selectionArgs){
        Cursor cursor = null;
        if (db!= null){
            cursor = db.rawQuery(sql,selectionArgs);
        }
        return cursor;
    }

    /**
     * 查询数据库的Cursor对象转换成list集合
     * @param cursor  游标对象
     * @return 集合对象
     */
    public static List<Person> cursorToList(Cursor cursor){
        List<Person> list = new ArrayList<>();
        //moveToNext（）如果返回true表示下一条数据存在，否则表示游标中的数据读取完毕
        while (cursor.moveToNext()){
            //getColumnIndex(String columnName) 根据参数中指定的字段名称获取字段下标
            int columnIndex = cursor.getColumnIndex(Constant._ID);
            //cursor.getInt(int columIndex) 根据参数中指定的字段下标  获取对应的int类型的value
            int _id = cursor.getInt(columnIndex);
            String name = cursor.getString(cursor.getColumnIndex(Constant.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(Constant.AGE));
            Person person = new Person(_id,name,age);
            list.add(person);
        }
        return list;
    }
    /**
     * 根据sql语句在数据库中执行语句
     * @param db 数据库对象
     * @param sql sql语句
     */
    public static void execSQL(SQLiteDatabase db,String sql){
        if (db != null) {
            if (sql != null && !"".equals(sql)){
                db.execSQL(sql);
            }
        }
    }

    /**
     * 根据数据库以及数据库的表名获取数据总条目
     * @param db  数据库对象
     * @param tableName  数据库表名
     * @return  数据库总条目
     */
    public static int getDataCount(SQLiteDatabase db,String tableName){
        int count = 0;
        if (db != null){
            Cursor cursor = db.rawQuery("select * from " + tableName,null);
            count = cursor.getCount();
        }
        return count;
    }

    /**
     * 根据当前页码查询获取该页码对应的集合数据
     * @param db  数据库对象
     * @param tableName  数据库中表的名称
     * @param currentPage 当前的页码
     * @return  当前页对应的数据集合
     * select * from person ?,?  如何根据当前页码获取该页数据
     * 0,20    1
     * 20,40   2
     * 40,60   3
     */
    public static List<Person> getListByCurrentPage(SQLiteDatabase db,String tableName
                                                    ,int currentPage,int pageSize){
        int index = (currentPage - 1) * pageSize;//获取当前页码第一条数据的下标
        Cursor cursor = null;
        if (db != null){
            String sql = "select * from "+ Constant.TABLE_NAME+ " limit ?,? ";
            cursor = db.rawQuery(sql,new String[]{index + "",pageSize+ ""});
        }
        return cursorToList(cursor);
    }
}
