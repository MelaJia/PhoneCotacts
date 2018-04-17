package cn.edu.gdmec.android.phonecotacts;

import android.content.Context;
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
    public SQLiteDatabase openConnectuin(){
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
            openConnectuin();
            db.execSQL(createTableSql);
        }catch (Exception e){
            e.printStackTrace();
            return false;

        }finally {
            closeConnection();
        }

        return true;

    }


    public boolean isTableExits(String tablename) {

        return false;
    }
}
