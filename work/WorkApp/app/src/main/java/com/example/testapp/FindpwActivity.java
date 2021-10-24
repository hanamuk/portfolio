package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FindpwActivity extends AppCompatActivity {

    private EditText SocialNum1;
    private EditText SocialNum2;
    private EditText BsNum;

    private String scnum1;
    private String scnum2;
    private String bsnum;

    private MakeJson Mjson;
    private String json;
    private int find_fail;

    private PasswordFindData data= new PasswordFindData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_findpw);

        BsNum = (EditText)findViewById(R.id.bsnum);
        SocialNum1 = (EditText)findViewById(R.id.scnum1);
        SocialNum2 = (EditText)findViewById(R.id.scnum2);
    }


    public void okBtnOnClick(View view) {

        String url = "http://220.89.167.212:8085/testing05/PassFinding";
        Mjson = new MakeJson();
        bsnum = BsNum.getText().toString();
        scnum1 = SocialNum1.getText().toString();
        scnum2 = SocialNum2.getText().toString();
        String result=null;
        JSONParser jsonParser = new JSONParser();

        //pwdata로 바꾸기


        String socialnum = scnum1+"-"+scnum2;

        //pwfindJOSN만들기
        json = Mjson.FindPasswordJson(bsnum,socialnum);


        NetworkTask networkTask = new NetworkTask(url, null,json);
        try
        {
            result = networkTask.execute().get();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //pwdata로
        data = jsonParser.FindPaswordJSONParse(result);

        find_fail = data.Getpwd_find_fail();

        if(find_fail ==0){
            //틀릴시
            Intent intent1 = new Intent(this, FailPwPopup.class);
            startActivity(intent1);
        }
        else {

            String pw =  jsonParser.FindPaswordJSONParse(result).GetORIGIN_ACCOUNT_PASSWORD();
            //맞을시
            Intent intent = new Intent(this, ShowPwPopup.class);
            intent.putExtra("data", pw);
            startActivity(intent);

        }

    }


    public void backBtnOnClick(View view){
        finish();
    }
}
