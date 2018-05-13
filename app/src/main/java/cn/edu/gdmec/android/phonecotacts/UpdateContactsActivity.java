package cn.edu.gdmec.android.phonecotacts;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by HP on 2018/4/22.
 */
public class UpdateContactsActivity extends AppCompatActivity {
    private EditText name;
    private EditText danwei;
    private EditText mobile;
    private EditText qq;
    private EditText address;
    //修改的联系人
    private User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_edit);
        setTitle("修改联系人");
        name = (EditText) findViewById(R.id.name);
        danwei = (EditText) findViewById(R.id.danwei);
        mobile = (EditText) findViewById(R.id.mobile);
        qq = (EditText) findViewById(R.id.qq);
        address = (EditText) findViewById(R.id.address);
            //需要修改的联系人界面数据显示
        Bundle localBunlde=getIntent().getExtras();
        int id=localBunlde.getInt("user_ID");
        ContactsTable ct=new ContactsTable(this);
        user=ct.getUserByID(id);
        name.setText(user.getName());
        mobile.setText(user.getMobile());
        qq.setText(user.getQq());
        danwei.setText(user.getDanwei());
        address.setText(user.getAddress());
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
                if (name.getText().toString().equals("")){
                    User user=new User();
                    user.setName(name.getText().toString());
                    user.setMobile(mobile.getText().toString());
                    user.setDanwei(danwei.getText().toString());
                    user.setAddress(address.getText().toString());
                    user.setQq(qq.getText().toString());
                    ContactsTable ct=new ContactsTable(UpdateContactsActivity.this);
                    //更新数据库联系人
                    if (ct.updateUser(user)){
                        Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(this, "修改失败！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "数据不能为空！", Toast.LENGTH_SHORT).show();
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
