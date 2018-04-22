package cn.edu.gdmec.android.phonecotacts;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
                return users.length;
            }

            @Override
            public Object getItem(int position) {
                return users[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }
//getView()逐一绘制每一行数据，返回视图
            //convertView 用于显示每行数据的视图，首先需要判断convertView是否为空
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView==null){
                    TextView textView=new TextView(MyContactsActivity.this);
                    textView.setTextSize(22);
                    convertView=textView;
                }
                //适配器显示的数据源是users数组
                String mobile=users[position].getMobile()==null?"":users[position].getMobile();
                ((TextView)convertView).setText(users[position].getName()+"--"+mobile);
                if (position==selectItem){
                    convertView.setBackgroundColor(Color.YELLOW);
                }else {
                    convertView.setBackgroundColor(0);
                }
                return convertView;
            }
        };

        //设置listview控件的适配器
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
        menu.add(Menu.NONE,2,Menu.NONE,"编辑");
        menu.add(Menu.NONE,3,Menu.NONE,"查看信息");
        menu.add(Menu.NONE,4,Menu.NONE,"删除");
        menu.add(Menu.NONE,5,Menu.NONE,"查询");
        menu.add(Menu.NONE,6,Menu.NONE,"导入到手机电话浦");
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
                //根据数据库ID判断当前记录是否可以操作
                if (users[selectItem].getId_DB()>0){
                intent=new Intent(MyContactsActivity.this,UpdateContactsActivity.class);
                startActivity(intent);
                }else {
                    Toast.makeText(this, "无结果记录，无法操作", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3://查看信息
                if (users[selectItem].getId_DB()>0){
                intent=new Intent(MyContactsActivity.this,ContactsMessageActivity.class);
                intent.putExtra("user_ID",users[selectItem].getId_DB());
                startActivity(intent);
                }else {
                    Toast.makeText(this, "无结果记录，无法操作！", Toast.LENGTH_SHORT).show();
                }
                break;
            case 4://删除
                if (users[selectItem].getId_DB()>0){
                delete();
                }else{
                    Toast.makeText(this, "无结果记录，无法操作！", Toast.LENGTH_SHORT).show();
                }
                break;
            case 5://查询
                new FindDialog(this).show();
                break;
            case 6:
                if (users[selectItem].getId_DB()>0){
                    importPhone(users[selectItem].getName(),users[selectItem].getMobile());
                    Toast.makeText(this, "已结成功导入"+users[selectItem].getName()+"到手机电话薄", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "无结果记录，无法操作！", Toast.LENGTH_SHORT).show();
                }
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

    private void importPhone(String name, String mobile) {

    }

    private void delete() {

    }

    private void initView() {
    }

    private class FindDialog extends Dialog{
        public FindDialog(Context context) {
            super(context);
        }
    }
}
