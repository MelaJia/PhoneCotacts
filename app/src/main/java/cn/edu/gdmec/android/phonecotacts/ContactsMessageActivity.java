package cn.edu.gdmec.android.phonecotacts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by HP on 2018/4/22.
 * 显示联系人界面
 */

public class ContactsMessageActivity extends AppCompatActivity {
    private TextView name;
    private TextView danwei;
    private TextView mobile;
    private TextView qq;
    private TextView address;
    private User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_message);

        setTitle("联系人信息");
        name = (TextView) findViewById(R.id.name);
        danwei = (TextView) findViewById(R.id.danwei);
        mobile = (TextView) findViewById(R.id.mobile);
        qq = (TextView) findViewById(R.id.qq);
        address = (TextView) findViewById(R.id.address);

        //将要修改的联系人数据赋值到用户界面显示
        Bundle localBunlde=getIntent().getExtras();
        int id=localBunlde.getInt("user_ID");
        ContactsTable ct=new ContactsTable(this);
        user=ct.getUserByID(id);
        name.setText("姓名:"+user.getName());
        mobile.setText("电话："+user.getMobile());
        qq.setText("QQ:"+user.getQq());
        danwei.setText("单位："+user.getDanwei());
        address.setText("地址："+user.getAddress());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        menu.add(Menu.NONE,1,Menu.NONE,"返回");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
