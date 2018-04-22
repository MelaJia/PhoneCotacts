package cn.edu.gdmec.android.phonecotacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
/**
 * 手机通信程序主界面
 * */
public class MyContactsActivity extends Activity {
//显示结果列表
    private ListView listView;
    private BaseAdapter listViewAdapter;   //ListView列表适配器
    private User users[];
    private int selectItem=0; //当前选择的数据项

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("通讯录");
        listView = (ListView) findViewById(R.id.listView);
        loadContacts();
    }
//加载联系人列表
    private void loadContacts() {
        ContactsTable ct=new ContactsTable(this);
        users=ct.getAllUser();
        //列表实现适配器
        listViewAdapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }
        };
        //设置listview控件
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //记录单击列的位置
                selectItem=i;
                //刷新列表
                listViewAdapter.notifyDataSetChanged();
            }
        });
    }
//    创建菜单
    public boolean onCreateOptionMenu(Menu menu){
        menu.add(Menu.NONE,1,Menu.NONE,"添加");
        menu.add(Menu.NONE,2,Menu.NONE,"添加");
        menu.add(Menu.NONE,3,Menu.NONE,"添加");
        menu.add(Menu.NONE,4,Menu.NONE,"添加");
        menu.add(Menu.NONE,5,Menu.NONE,"添加");
        menu.add(Menu.NONE,6,Menu.NONE,"添加");
        menu.add(Menu.NONE,7,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);

    }
//    菜单点击事件
    public boolean onOptionItemSelected(MenuItem item){
        switch(item.getItemId()){
            case 1://添加
                Intent intent=new Intent(MyContactsActivity.this,AddContactsActivity.class);
                startActivity(intent);
                break;
            case 2://编辑
                break;
            case 3://查看信息
                break;
            case 4://删除
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                finish();
              //  System.exit(0);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initView() {
    }
}
