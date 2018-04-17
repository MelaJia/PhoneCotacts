package cn.edu.gdmec.android.phonecotacts;

/**
 * Created by apple on 18/4/17.
 * 联系人信息类
 */

class User {
    public final static String NAME="name";
    public final static String MOBILE="mobile";
    public final static String DANWEI="danwei";
    public final static String QQ="qq";
    public final static String ADDRESS="address";
    private String name;  //用户名
    private String mobile;
    private String danwei;
    private String qq;
    private String address;
    private int id_DB=-1; //表主键

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getMobile() {
        return mobile;
    }
    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }
    public String getDanwei() {
        return danwei;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getQq() {
        return qq;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }


    public void setId_DB(int id_DB) {
        this.id_DB = id_DB;
    }
    public int getId_DB() {
        return id_DB;
    }





}
