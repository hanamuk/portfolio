package com.example.testapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingActivity extends AppCompatActivity {

    private TextView processtxt;
    private TextView equiptxt;
    private EditText iptxt;
    private EditText porttxt;
    private ImageView autologinbtn;

    private Boolean isautologin;
    public static String Process; //공정
    public static int processid;
    public static String equipment; //설비
    public static String ip;
    public static String port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);

        processtxt = (TextView)findViewById(R.id.process_spinner_txt);
        equiptxt = (TextView)findViewById(R.id.equip_spinner_txt);
        iptxt = (EditText) findViewById(R.id.ip_txtbox);
        porttxt = (EditText)findViewById(R.id.port_txtbox);
        autologinbtn = (ImageView) findViewById(R.id.autologin_img);

        SharedPreferences setting = getSharedPreferences("setting", MODE_PRIVATE);
        Process = setting.getString("PROCESS","");
        equipment = setting.getString("EQUIPPMENT","");
        ip = setting.getString("IP","");
        port = setting.getString("PORT","");
        processid = setting.getInt("PROCESSID", 0);

        SharedPreferences auto = getSharedPreferences("auto", MODE_PRIVATE);
        isautologin = auto.getBoolean("AUTO", true);



        iptxt.setText("" + ip);
        porttxt.setText("" + port);
        processtxt.setText("" + Process);
        equiptxt.setText("" + equipment);



        if(isautologin ==false)
        {
            autologinbtn.setImageResource(R.drawable.btn_switch_off);
        }else
        {
            autologinbtn.setImageResource(R.drawable.btn_switch_on);

        }



        /*iptxt.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    iptxt.setText(null);
                }
            }
        });

        porttxt.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    porttxt.setText(null);
                }
            }
        });*/


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // MainActivity 에서 요청할 때 보낸 요청 코드 (3000)
                case 3000:
                    if(data.getIntExtra("num", 0) == 1) {
                        if(data.getStringExtra("result") != null)
                        {
                            processtxt.setText(data.getStringExtra("result"));
                            processid = data.getIntExtra("processid", 0);
                        }
                    }
                    if(data.getIntExtra("num", 0) == 2)
                        if(data.getStringExtra("result") != null)
                        {
                            equiptxt.setText(data.getStringExtra("result"));
                        }


                    break;
            }
        }
    }


    //자동로그인
    public void autoLoginBtnOnClick(View view){
        if (isautologin  == true)
        {
            autologinbtn.setImageResource(R.drawable.btn_switch_off);
            isautologin  = false;


        }
        else {
            autologinbtn.setImageResource(R.drawable.btn_switch_on);
            isautologin  = true;

        }
    }

    public void backBtnOnClick(View view){

        Process = processtxt.getText().toString();
        equipment = equiptxt.getText().toString();
        ip = iptxt.getText().toString();
        port= porttxt.getText().toString();

        SharedPreferences setting = getSharedPreferences("setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();
        editor.putString("PROCESS", Process);
        editor.putString("EQUIPPMENT", equipment);
        editor.putString("IP", ip);
        editor.putString("PORT", port);
        editor.putInt("PROCESSID", processid);
        editor.commit();


        SharedPreferences auto = getSharedPreferences("auto", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = auto.edit();
        editor2.putBoolean("AUTO", isautologin);
        editor2.commit();
        finish();
    }

    public void processSelectBtnOnClick(View view) {
        Intent intent = new Intent( getApplicationContext(),SettingPopup.class);
        intent.putExtra("data", "공정");
        intent.putExtra("num", 1);
        startActivityForResult( intent , 3000);
    }


    public void equipSelectBtnOnClick(View view) {
        Intent intent = new Intent( getApplicationContext(),SettingPopup.class);
        intent.putExtra("data", "설비");
        intent.putExtra("num", 2);
        startActivityForResult( intent , 3000);
    }


}