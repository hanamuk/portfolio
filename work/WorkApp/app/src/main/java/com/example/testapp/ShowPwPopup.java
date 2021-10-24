package com.example.testapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowPwPopup extends Activity {

    private String password;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_showpw);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Intent intent = getIntent();
        password = intent.getStringExtra("data");


        message = (TextView)findViewById(R.id.showmsg);
        message.setText(String.format("현재 비밀번호는 %s 입니다.", password));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void okBtnPressed(View view)  {
        finish();
    }
}