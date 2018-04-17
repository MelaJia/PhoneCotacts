package cn.edu.gdmec.android.phonecotacts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

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

    private void loadContacts() {
        ContactsTable ct=new ContactsTable(this);
    }

    private void initView() {
    }
}
