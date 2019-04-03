package com.example.ch05;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class UserlistActivity extends ListActivity {

    private ListView lv;

    private ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();

    private String[] user_names={"张三","李四","王五"};
    private String[] user_tels={"13545236548","13345678542","18645124785"};
    private Integer[] pic_pics={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3};

    private SimpleAdapter listAdapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        for(int i=0;i<user_names.length;i++){
            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("user_pic", pic_pics[i]);
            map.put("user_name", user_names[i]);
            map.put("user_tel", user_tels[i]);
            list.add(map);
        }

        listAdapter=new SimpleAdapter(
                this,
                list,
                R.layout.userlist,
                new String[]{"user_name","user_tel","user_pic"},
                new int[]{R.id.user_name,R.id.user_tel,R.id.user_pic}
                );

        setListAdapter(listAdapter);

        lv=getListView();
        registerForContextMenu(lv);

        //绑定置底按钮
        View btview=View.inflate(UserlistActivity.this, R.layout.listview_button, null);
        Button returnbt=btview.findViewById(R.id.returnbt);
        returnbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv.addFooterView(btview);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        menu.add(1,0,0,"编辑联系人");
        menu.add(1,1,1,"删除联系人");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo;
        menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case 0:

                Toast.makeText(UserlistActivity.this, "编辑第" + menuInfo.id + "项", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                int pos = (int) getListAdapter().getItemId(menuInfo.position);
                list.remove(pos);
                listAdapter.notifyDataSetChanged();
                Toast.makeText(UserlistActivity.this, "删除第" + menuInfo.id + "项", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }
}
