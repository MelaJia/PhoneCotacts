package cn.edu.gdmec.android.phonecotacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 18/4/17.
 * SQLite数据可操作管理
 */

public class MyDB extends SQLiteOpenHelper{
    private static String DB_NAME="MY_DB.db";
    private static int DB_VERSION=2;
    private SQLiteDatabase db;//创建数据库对象

    public MyDB(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
        db=getWritableDatabase();

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
//    执行数据库链接
    public SQLiteDatabase openConnection(){
        if (!db.isOpen()){
            db=getWritableDatabase();
        }
        return db;
    }
//    关闭数据库
    public void closeConnection(){
      try{
          if (db!=null&&db.isOpen()){
              db.close();
          }
      }catch (Exception e){
          e.printStackTrace();
      }

    }
//    创建表
    public boolean createTable(String createTableSql){
        try{
            //1、执行数据库连接
            openConnection();
            //2、执行语句
            db.execSQL(createTableSql);
        }catch (Exception e){
            e.printStackTrace();
            return false;

        }finally {
            closeConnection();
        }

        return true;

    }

    /**
     * 添加操作
     * */
    public boolean save(String tableName, ContentValues values){
        try{
            openConnection();
            db.insert(tableName,null,values);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;


    }

    /**
     * 更新操作
     * */
    public boolean update(String table, ContentValues values,String whereClause,String []whereArgs){
        try{
            openConnection();
            db.update(table,values,whereClause,whereArgs);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;


    }
    /**
     * 删除操作
     * @param deleteSql 对应删除的条件
     * @param obj 对应删除条件的值
     * */
    public boolean delete(String table, String deleteSql,String obj[]){
        try{
            openConnection();
            db.delete(table,deleteSql,obj);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;


    }
    /**
     * 查询操作
     *
     * */
    /**
     * 添加操作
     * */
    public Cursor find(String findSql, String obj[]){
        try{
            openConnection();
            Cursor cursor=db.rawQuery(findSql,obj);
            return cursor;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }



    }


    public boolean isTableExits(String tablename) {
        try {
            openConnection();
            String str = "select count(*) from "+tablename;
            db.rawQuery(str, null).close();
        }catch (Exception e){
            return false;
        }

        return true;
    }
}
