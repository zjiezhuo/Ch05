package com.example.ch05;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity {

    private EditText username=null;
    private EditText userpwd=null;
    private RadioGroup genderGroup=null;
    private RadioButton femaleButton=null;
    private RadioButton maleButton=null;
    private CheckBox readck=null;
    private EditText birthday=null;
    private EditText userjob=null;
    private EditText userlove=null;
    private Button submitbt=null;
    private Button readbt=null;

    /**
     * 格式化日期
     *
     * @param y
     *            Year
     * @param m
     *            Month
     * @param d
     *            Day
     * @return yyyy-MM-dd
     */
    public String formatDate(int y, int m, int d) {
        StringBuffer buf = new StringBuffer();
        buf.append(y).append("-");
        if (m < 10) {
            buf.append("0" + m);
        } else {
            buf.append(m);
        }
        buf.append("-");
        if (d < 10) {
            buf.append("0" + d);
        } else {
            buf.append(d);
        }
        return buf.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        femaleButton=(RadioButton)findViewById(R.id.femaleButton);
        maleButton=(RadioButton)findViewById(R.id.maleButton);

        username=(EditText)findViewById(R.id.username);
        userpwd=(EditText)findViewById(R.id.userpwd);

        submitbt=(Button)findViewById(R.id.submitbt);

        //单选按钮监听
        genderGroup=(RadioGroup)findViewById(R.id.genderGroup);
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(femaleButton.getId() == checkedId){
                    Toast.makeText(FormActivity.this, getResources().getString(R.string.female), Toast.LENGTH_SHORT).show();
                }
                if(maleButton.getId() == checkedId){
                    Toast.makeText(FormActivity.this, getResources().getString(R.string.male), Toast.LENGTH_SHORT).show();
                }

            }
        });

        //多选按钮监听
        readck=(CheckBox)findViewById(R.id.readck);
        readck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    submitbt.setEnabled(true);
                }else{
                    submitbt.setEnabled(false);
                }
            }
        });



        //爱好多选对话框绑定
        userlove=findViewById(R.id.userlove);
        userlove.setInputType(InputType.TYPE_NULL);         //点击editview不弹出软键盘
        userlove.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final String[] loves=new String[]{"运动","网络","阅读","旅游","摄影","烹饪"};
                final boolean[] chsBool = {false,true,false,false,false,false};

                new AlertDialog.Builder(FormActivity.this).setTitle("多选对话框")
                        .setMultiChoiceItems(loves, chsBool, new DialogInterface.OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                            }
                        })
                        .setPositiveButton(R.string.confirm,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String mylovestr="";
                                for(int i=0;i<loves.length;i++){
                                    if(chsBool[i]){
                                        mylovestr=loves[i]+"、"+mylovestr;
                                    }
                                }
                                userlove.setText(mylovestr);
                            }
                        })
                        .setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }
        });

        //职业单选对话框绑定
        userjob=(EditText)findViewById(R.id.userjob);
        userjob.setInputType(InputType.TYPE_NULL);         //点击editview不弹出软键盘
        userjob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String[] jobs=new String[]{"学生","教师","工程师","医生","律师","文员","记者","国家干部"};
                new AlertDialog.Builder(FormActivity.this)
                        .setTitle("请选择您的职业：")
                        .setItems(jobs,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                String myjob=jobs[which];
                                userjob.setText(myjob);
                            }
                        }).create().show();
            }
        });

        //日期选择框绑定

        birthday=(EditText)findViewById(R.id.userbirthday);
        birthday.setInputType(InputType.TYPE_NULL);         //点击editview不弹出软键盘
        birthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        FormActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String fDate = formatDate(year,
                                        monthOfYear + 1, dayOfMonth);
                                birthday.setText(fDate);
                            }
                        }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now
                        .get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        //阅读按钮监听器
        readbt=(Button)findViewById(R.id.readbt);
        readbt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(FormActivity.this, TextActivity.class);
                startActivity(intent);
            }});

        //提交按钮事件监听
        submitbt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String username_str=username.getText().toString();
                String userpwd_str=userpwd.getText().toString();
                String userlove_str=userlove.getText().toString();
                String userjob_str=userjob.getText().toString();
                String userbirthday_str=birthday.getText().toString();
                RadioButton checkButton = (RadioButton)findViewById(genderGroup.getCheckedRadioButtonId());
                String usersex_str=checkButton.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("username", username_str);
                intent.putExtra("userpwd", userpwd_str);
                intent.putExtra("userlove", userlove_str);
                intent.putExtra("userjob", userjob_str);
                intent.putExtra("userbirthday", userbirthday_str);
                intent.putExtra("usersex", usersex_str);
                intent.setClass(FormActivity.this, ShowActivity.class);
                startActivity(intent);
            }

        });


    }
}
