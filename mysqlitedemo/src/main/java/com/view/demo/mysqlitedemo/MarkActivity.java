package com.view.demo.mysqlitedemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.view.demo.mysqlitedemo.bean.Person;
import com.view.demo.mysqlitedemo.utils.Constant;
import com.view.demo.mysqlitedemo.utils.DbManager;
import com.view.demo.mysqlitedemo.utils.MySqliteHelper;

import java.io.File;
import java.util.List;

/**
 *
 */
public class MarkActivity extends AppCompatActivity {

    private MySqliteHelper helper;
    private ListView lv;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
        helper = DbManager.getInstance(this);
        initViews();
        setContent();
    }

    private void setContent() {
        //1.获取数据库查询的数据源
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator +"info.db";
        /*openDatabase(String path, SQLiteDatabase.CursorFactory factory, int flags)
        * String path 表示当前打开的数据库的存储路径
        * CursorFactory factory
        * int flags 表示打开数据库的操作模式
        * */
        db = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("select * from " + Constant.TABLE_NAME,null);

        //2.将数据源的数据加载到适配器中
        // SimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags)
        /*Context context 上下文对象
        *   int layout   表示适配器中控件的每项item的布局id
        *   Cursor c    表示Cursor数据源
        *   String[] from  表示cursor中数据表字段的数据
        *   int[] to 表示展示字段的对应的控件的资源的id
        *   int flags 设置适配器的标记
        * */
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.layout_item_person
                ,cursor,new String[]{Constant._ID,Constant.NAME,Constant.AGE},
                new int[]{R.id.tv_id,R.id.tv_name,R.id.tv_age},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        //3.将适配器中的数据加载到控件中
        //注意：若使用SimpleCursorAdapter，必须包含主键 _ID
        lv.setAdapter(cursorAdapter);
        db.close();
    }

    private void initViews() {
        lv = (ListView) findViewById(R.id.lv);
    }

    /*
    * 点击创建数据库
    * */
    public void doCreateDB(View view){
        /*
        getReadableDatabase()和getWritableDatabase() 创建或打开数据库
        如果数据库不存在则创建数据库，如果数据库存在就直接打开数据库
        默认情况下这两个函数都表述打开或者创建可读可写的数据库
        如果磁盘已满或者数据库本身的权限等情况下，getReadableDatabase()
        就是打开的是只读数据库
         */
       SQLiteDatabase db = helper.getReadableDatabase();
        db = helper.getWritableDatabase();
        for (int i = 1; i < 30; i++) {
            String sql = "insert into " + Constant.TABLE_NAME + " values(" + i+ ",'张三"+ i +"',20)";
            db.execSQL(sql);
        }
        db.close();
    }

    public void click(View view){
        switch (view.getId()){
            case R.id.btn_insert:
                SQLiteDatabase db = helper.getWritableDatabase();
                String sql = "insert into " + Constant.TABLE_NAME +" values(31,'zhangsan',20)";
                DbManager.execSQL(db,sql);
                String sql2 = "insert into " + Constant.TABLE_NAME + " values(32,'lisi',25)";
                DbManager.execSQL(db,sql2);
                db.close();
                break;
            case R.id.btn_updata:
                db = helper.getWritableDatabase();
                String updatesql = "update " + Constant.TABLE_NAME +" set "
                        + Constant.NAME + "= 'xiaoming ' where " + Constant._ID + "= 1" ;
                DbManager.execSQL(db,updatesql);
                db.close();
                break;
            case R.id.btn_delete:
                db = helper.getWritableDatabase();
                String delsql = "delete from " + Constant.TABLE_NAME + " where "
                        + Constant._ID + "= 2";
                DbManager.execSQL(db,delsql);
                db.close();
                break;
            case R.id.btn_query://点击按钮查询表中的数据
                db = helper.getWritableDatabase();
                String selectSql = "select * from " + Constant.TABLE_NAME;
                Cursor cursor = DbManager.selectDataBySql(db,selectSql,null);
                List<Person> list = DbManager.cursorToList(cursor);
                for (Person p: list)
                {
                    Log.i("tag",p.toString());
                }
                db.close();
                break;
            /**
             * 通过Android的系统自带的API封装进行数据库的操作
             */
            case R.id.btn_insertapi://通过系统的api提供的方式操作
                db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Constant._ID,33);
                values.put(Constant.NAME,"张三");
                values.put(Constant.AGE,36);
                long result = db.insert(Constant.TABLE_NAME,null,values);
                if (result > 0){
                    Toast.makeText(MarkActivity.this,"插入数据成功！"+result,Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MarkActivity.this,"插入数据失败！"+ result,Toast.LENGTH_LONG).show();
                }
                db.close();
                break;
            case R.id.btn_updatepi:
                db = helper.getWritableDatabase();
                /**
                 * update(String table,ContentValues values,String whereClause,String[] whereArgs)
                 * String table 表示修改的数据表的名称
                 * ContentValues values 表示键为String类型的hashmap
                 * String whereClause 表示修改条件
                 * String[] whereArgs  表示修改条件的占位符
                 * 返回修改后的条数
                 */
                ContentValues values1 = new ContentValues();
                values1.put(Constant.NAME,"夜色");//put（需要修改的字段名称，修改后的字段值）
//                int count = db.update(Constant.TABLE_NAME,values1,Constant._ID + "= 3",null);
                int count = db.update(Constant.TABLE_NAME,values1,Constant._ID + "=?",new String[]{"3"});
                if (count > 0){
                    Toast.makeText(MarkActivity.this,"修改数据成功！"+count,Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MarkActivity.this,"修改数据失败！"+ count,Toast.LENGTH_LONG).show();
                }
                db.close();
                break;
            case R.id.btn_deletepi:
                db = helper.getWritableDatabase();
                /**
                 * delete (String table ,String whereClause,String[] whereArgs)
                 * String table 表示删除的数据表的名称
                 * String whereClause 表示删除的条件
                 * String whereArgs 表示删除的条件的占位符
                 * 返回删除数据的条数
                 */
                int count2 = db.delete(Constant.TABLE_NAME,Constant._ID + "= ?",new String[]{"1"});
                if (count2 > 0){
                    Toast.makeText(MarkActivity.this,"修改数据成功！"+count2,Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MarkActivity.this,"修改数据失败！"+ count2,Toast.LENGTH_LONG).show();
                }
                db.close();
                break;
            case R.id.btn_queryapi://api查询数据库
                db = helper.getWritableDatabase();
               /* query(String table, String[] columns,
               String selection, String[] selectionArgs,
                String groupBy, String having, String orderBy) {
                String table 表示查询的表名
                String[] columns  表示查询表中的字段名称 null 表示查询所有
                String selection  表示查询条件 where语句
                String[] selectionArgs 表示查询条件的占位符的取值
                String groupBy 表示分组条件 group by 语句
                String having 表示筛选条件 having 语句
                String orderBy 表示排序条件 order by 语句
*/
                Cursor cursor2 = db.query(Constant.TABLE_NAME,null,Constant._ID + ">? "
                ,new String[]{"10"},null,null,Constant._ID + " desc");
                list = DbManager.cursorToList(cursor2);
                for (Person p: list)
                {
                    Log.i("tag",p.toString());
                }
                db.close();
                break;
            case R.id.btn_cursorAdapter:
                startActivity(new Intent(MarkActivity.this,CursorAdapterActivity.class));
                break;
            case R.id.btn_transation:
                startActivity(new Intent(MarkActivity.this,TransactionActivity.class));
                break;
        }
    }
}
