package com.example.ch05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.nio.BufferUnderflowException;

public class ShowActivity extends AppCompatActivity {

    private TextView username=null;
    private TextView userpwd=null;
    private TextView usersex=null;
    private TextView userbirthday=null;
    private TextView userlove=null;
    private TextView userjob=null;
    private Button backbt=null;
    private Button confirmbt=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        username=(TextView)findViewById(R.id.usernameshow);
        userpwd=(TextView)findViewById(R.id.userpwdshow);
        usersex=(TextView)findViewById(R.id.usersexshow);
        userjob=(TextView)findViewById(R.id.userjobshow);
        userlove=(TextView)findViewById(R.id.userloveshow);
        userbirthday=(TextView)findViewById(R.id.userbirthdayshow);

        Intent intent=getIntent();
        String get_username=intent.getStringExtra("username");
        String get_userpwd=intent.getStringExtra("userpwd");
        String get_usersex=intent.getStringExtra("usersex");
        String get_userjob=intent.getStringExtra("userjob");
        String get_userlove=intent.getStringExtra("userlove");
        String get_userbirthday=intent.getStringExtra("userbirthday");

        username.setText(get_username);
        userpwd.setText(get_userpwd);
        usersex.setText(get_usersex);
        userjob.setText(get_userjob);
        userlove.setText(get_userlove);
        userbirthday.setText(get_userbirthday);

        backbt=(Button)findViewById(R.id.backbt);
        backbt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }

        });

        confirmbt=findViewById(R.id.confirmbt);
        confirmbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ShowActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
