package cn.edu.gdmec.android.phonecotacts;

import android.content.Context;

/**
 * Created by apple on 18/4/17.
 * 用于封装联系人表中数据的操作
 */

public  class ContactsTable {
    private final static String TABLENAME="contactsTable"; //表名
    private MyDB db; //定义SQLitOpenHelper数据库管理对象
   public ContactsTable(Context context) {
//        db=new MyDB(context);
//        if (!db.isTableExits(TABLENAME)){
//            String  createTableSql="CREATE TABLE IF NOT EXISTS" +
//                    TABLENAME+"(id_DB integer" +
//                    "primary key AUTOINCREMENT," +
//                    User.NAME+" VARCHAR," +
//                    User.MOBILE+""
//        }
   }
}
