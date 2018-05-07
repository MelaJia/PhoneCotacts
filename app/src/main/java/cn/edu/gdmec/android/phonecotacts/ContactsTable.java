package cn.edu.gdmec.android.phonecotacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.io.PushbackInputStream;
import java.util.Vector;

/**
 * Created by apple on 18/4/17.
 * 用于封装联系人表中数据的操作
 */

public  class ContactsTable {
    private final static String TABLENAME="contactsTable"; //表名
    private MyDB db; //定义SQLitOpenHelper数据库管理对象

   public ContactsTable(Context context) {
        db=new MyDB(context);
        if (!db.isTableExits(TABLENAME)){
            String  createTableSql="CREATE TABLE IF NOT EXISTS" +
                    TABLENAME + "(id_DB integer" +
                    "primary key AUTOINCREMENT," +
                    User.NAME + " VARCHAR," +
                    User.MOBILE + "VARCHAR," +
                    User.QQ+"VARCHAR," +
                    User.DANWEI+"VARCHAR," +
                    User.ADDRESS+"VARCHAR" +
                    ")";
            db.createTable(createTableSql);

        }

   }
   public boolean addData(User user){
       ContentValues values=new ContentValues();
       values.put(User.NAME,user.getName());
       values.put(User.MOBILE,user.getMobile());
       values.put(User.DANWEI,user.getDanwei());
       values.put(User.QQ,user.getQq());
       values.put(User.ADDRESS,user.getAddress());
       return db.save(TABLENAME,values);

   }
//获取联系人表数据
    public User[] getAllUser() {
       //定义一个专门存放User类型数据Vector的向量类对象
        Vector<User> v=new Vector<User>();
        Cursor cursor=null;
        try {
            cursor=db.find("select * from "+TABLENAME,null);
            while (cursor.moveToNext()) {
                User temp = new User();
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
                //将符合查询条件的User对象添加到向量集合中
                v.add(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null){
                cursor.close();

            }
            db.closeConnection();
        }
        if (v.size()>0){
            //将vector中的数据，强制以User数组形式返回
            return v.toArray(new User[] {});

        }else {
            User[] users=new User[1];
            User user=new User();
            user.setName("无结果");
            users[0]=user;
            return users;

        }
    }
//    根据数据库改变主键ID来获取联系人
    public User getUserByID(int id){
       Cursor cursor=null;
       try {
           cursor=db.find("select * from "+TABLENAME+" where "
           +"id_DB=?",new String[]{id+""});
           User temp=new User();
           cursor.moveToNext();
           temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
           temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
           temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
           temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
           temp.setQq(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
           temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
           return temp;
       }catch (Exception e)
       {
           e.printStackTrace();
       }finally {
           if (cursor!=null){
               cursor.close();
           }
           db.closeConnection();
       }
       return null;
    }

    public User[] findUserByKey(String key){
        Vector<User> v=new Vector<User>();
        Cursor cursor=null;
        try {
            cursor=db.find("select * from "+TABLENAME+" where "
                    +User.NAME+" like '%"+key+"%'"+
                    " or "+User.MOBILE+" like '%"+key+"%'"+
                    " or "+User.MOBILE+" like '%"+key+"%'",null);
            while (cursor.moveToNext()) {
                User temp = new User();
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
                //将符合查询条件的User对象添加到向量集合中
                v.add(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null){
                cursor.close();

            }
            db.closeConnection();
        }
        if (v.size()>0){
            //将vector中的数据，强制以User数组形式返回
            return v.toArray(new User[] {});

        }else {
            User[] users=new User[1];
            User user=new User();
            user.setName("无结果");
            users[0]=user;
            return users;

        }

    }
    public boolean updateUser(User user){
        ContentValues values=new ContentValues();
        values.put(User.NAME,user.getName());
        values.put(User.MOBILE,user.getMobile());
        values.put(User.DANWEI,user.getDanwei());
        values.put(User.QQ,user.getQq());
        values.put(User.ADDRESS,user.getAddress());


        return db.update(TABLENAME,values," id_DB=?",new String[]{user.getId_DB()+""});
    }

    public boolean deleteByUser(User user) {
        return db.delete(TABLENAME," id_DB=?",new String[]{user.getId_DB()+""});
    }
}
