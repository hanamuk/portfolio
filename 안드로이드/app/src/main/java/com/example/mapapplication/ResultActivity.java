package com.example.mapapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ResultActivity extends AppCompatActivity {

    private TextView tv_result;//닉네임 text
    private ImageView iv_profile;//이미지 뷰
    private Button btn_move; // 이동 버튼


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent=getIntent();
        String nickName=intent.getStringExtra("nickName");
        String photoUrl=intent.getStringExtra("photoUrl");

        tv_result=findViewById(R.id.tv_result);
        tv_result.setText(nickName);

        iv_profile=findViewById(R.id.iv_profile);
        Glide.with(this).load(photoUrl).into(iv_profile);

        //이동 버튼---------------------------
        btn_move=findViewById(R.id.btn_move);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ResultActivity.this,MapsActivity.class);//현재 엑티비티 ->이동할 엑티비티
                startActivity(intent1);//엑티비티 이동 해주는 구문.
            }
        });
    }
}