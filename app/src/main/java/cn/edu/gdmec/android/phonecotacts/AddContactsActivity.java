package cn.edu.gdmec.android.phonecotacts;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by HP on 2018/4/22.
 * 显示添加数据界面
 */

public class AddContactsActivity extends AppCompatActivity {
    private EditText name;
    private EditText danwei;
    private EditText mobile;
    private EditText qq;
    private EditText address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_edit);
        initView();

    }

    private void initView() {
        name = (EditText) findViewById(R.id.name);
        danwei = (EditText) findViewById(R.id.danwei);
        mobile = (EditText) findViewById(R.id.mobile);
        qq = (EditText) findViewById(R.id.qq);
        address = (EditText) findViewById(R.id.address);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"保存");
        menu.add(Menu.NONE,2,Menu.NONE,"返回");

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                if (!name.getText().toString().equals("")){
                    User user=new User();
                    user.setName(name.getText().toString());
                    user.setMobile(mobile.getText().toString());
                    user.setQq(qq.getText().toString());
                    user.setDanwei(danwei.getText().toString());
                    user.setAddress(address.getText().toString());
                    ContactsTable ct=new ContactsTable(AddContactsActivity.this);
                    if (ct.addData(user)){
                        Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(this, "添加失败！", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(this, "请先输入数据", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
