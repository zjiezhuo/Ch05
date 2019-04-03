package com.example.ch05;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btlinkform;
    private Button btdailog;
    private Button btlinklist;
    private Button btlinktab;
    private Button btlinkslide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //表单
        btlinkform=(Button)findViewById(R.id.btlinkform);
        btlinkform.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, FormActivity.class);
                MainActivity.this.startActivity(intent);
            }

        });

        //普通对话框
        btdailog=findViewById(R.id.btdailog);
        btdailog.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //普通对话框
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("普通对话框")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"操作已取消",Toast.LENGTH_LONG).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"信息已删除",Toast.LENGTH_LONG).show();
                            }
                        })
                        .setMessage("确认删除？")
                        .create();
                dialog.show();
            }
        });

        btlinklist=findViewById(R.id.btlinklist);
        btlinklist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, UserlistActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        btlinktab=findViewById(R.id.btlinktab);
        btlinktab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, TabhostActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        btlinkslide=findViewById(R.id.btlinkslide);
        btlinkslide.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, SlideActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }
}
