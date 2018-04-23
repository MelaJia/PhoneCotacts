package cn.edu.gdmec.android.phonecotacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 手机通信程序主界面
 * */
public class MyContactsActivity extends AppCompatActivity {
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
       // onCreateOptionMenu(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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



    @Override
    protected void onResume() {
        super.onResume();
        ContactsTable ct=new ContactsTable(this);
        users=ct.getAllUser();
        //刷新列表
        listViewAdapter.notifyDataSetChanged();
    }


    private void initView() {
    }

/**
 * 查询
 * 在主页面弹出对话框
 * */
    public class FindDialog extends Dialog {

        private Button find;
        private Button cancel;

        public FindDialog(Context context) {
            super(context);
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_find);
            setTitle("查询联系人");
            find=findViewById(R.id.find);
            cancel=findViewById(R.id.cancel);
            find.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText value=findViewById(R.id.value);
                    ContactsTable ct=new ContactsTable(MyContactsActivity.this);
                    users=ct.findUserByKey(value.getText().toString());
                    for (int i=0;i<users.length;i++){
                        System.out.println("姓名是"+users[i].getName()+",电话是"+users[i].getMobile());
                    }
                    listViewAdapter.notifyDataSetChanged();
                    selectItem=0;
                    dismiss();
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });

        }

    }
    /*
    * 删除联系人
    * */
    public void delete(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("系统信息");
        builder.setMessage("是否删除联系人?");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ContactsTable ct=new ContactsTable(MyContactsActivity.this);
                //删除联系人
                if (ct.deleteByUser(users[selectItem])){
                    //重新获取数据
                    users=ct.getAllUser();
                    //刷新列表
                    listViewAdapter.notifyDataSetChanged();
                    selectItem=0;
                    Toast.makeText(MyContactsActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MyContactsActivity.this, "删除失败！", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    /**
     * 导入到手机电话薄
     * */
    public void importPhone(String name,String phone){
        //系统通讯录
      //  Uri
    }


}


