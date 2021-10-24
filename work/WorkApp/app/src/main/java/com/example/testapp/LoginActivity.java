package com.example.testapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private long backBtnTime=0;
    private EditText id;
    private EditText pw;
    private MakeJson Mjson;
    private String json;
    public static String MemberName;
    public static int division;
    public static int accountid;

    private String Id;
    private String Pw;
    private String autoId;
    private String autoPw;
    private boolean isFirst;
    private boolean isautologin= false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        isFirst= CheckAppFirstExecute();


        SharedPreferences auto = getSharedPreferences("auto", MODE_PRIVATE);
        isautologin = auto.getBoolean("AUTO",true);



        if(isFirst == false)
        {
            if(isautologin == true)
            {
               AutoLogin();

            }

        }

        id = (EditText)findViewById(R.id.id);
        pw = (EditText)findViewById(R.id.pw);

        id.setText(auto.getString("ID","0"));
        pw.setText(auto.getString("PW","0"));



    }

    //앱최초실행확인 (true - 최초실행) //클래스를 새로만들면 안돌아감;
    public boolean CheckAppFirstExecute(){
        SharedPreferences pref = getSharedPreferences("IsFirst" , Activity.MODE_PRIVATE);
        isFirst = pref.getBoolean("isFirst", false);
        if(!isFirst){ //최초 실행시 true 저장
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst", true);
            editor.commit();


        }

        return !isFirst;


    }


    @Override
    public void onBackPressed() {

        long currentTime = System.currentTimeMillis();
        long gapTime = currentTime -backBtnTime;
        if( 0 <= gapTime && 2000 >=gapTime){
            super.onBackPressed();
        }else{
            backBtnTime = currentTime;
            Toast.makeText(this, "뒤로 가기를 한번더 누르면 종료 됩니다.",Toast.LENGTH_SHORT ).show();
        }
    }


    //자동 로그인시
    public void AutoLogin()
    {
        String url = "http://220.89.167.212:8085/testing05/LoginReturn";
        Mjson = new MakeJson();

        //자동 저장된 값을 가지고있는다..

        SharedPreferences auto = getSharedPreferences("auto", MODE_PRIVATE);
        autoId = auto.getString("ID","0");
        autoPw = auto.getString("PW","0");


        String result=null;

        JSONParser jsonParser = new JSONParser();
        LoginData data= new LoginData();

        json = Mjson.LoginJson(autoId,autoPw);
        NetworkTask networkTask = new NetworkTask(url, null,json);
        try
        {
            result = networkTask.execute().get();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        data = jsonParser.LoginJSONParse(result);

        //TODO: 로그인 정보 틀릴시 -> ppt 6쪽 팝업띄우기(제가 띄울게여 걍 이프문 하나만 열어주세여)
        if(data.Getlogin_fail()==0){
            //틀릴시
            Intent intent = new Intent(this, LoginFailPopup.class);
            startActivity(intent);
        }

        //TODO: 로그인 정보 맞을시 -> ppt 7쪽 페이지 인텐트 시키기(이거도 걍 이픔문 열어주시면 걍제가 페이지 만들어서 인텐트 시킬게영)
        else {
            Intent intent = new Intent(this, MainActivity.class);
            MemberName = data.GetMember_name();
            division = data.GetORIGIN_ACCOUNT_DIVISION_ID();
            accountid = data.GetORIGIN_ACCOUNT_ID();
            startActivity(intent);
        }


    }


    //sign in 버튼 클릭
    public void loginBtnOnClick(View view) {


       /* String url = "https://webhook.site/eb6c350f-fb94-4c45-9da7-eaae968b0cb8";

        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();*/

        //TODO: 로그인 정보 틀릴시 -> ppt 6쪽 팝업띄우기(제가 띄울게여 걍 이프문 하나만 열어주세여)
        //TODO: 로그인 정보 맞을시 -> ppt 7쪽 페이지 인텐트 시키기(이거도 걍 이픔문 열어주시면 걍제가 페이지 만들어서 인텐트 시킬게영)



        //틀릴시
        /**/

        String url = "http://220.89.167.212:8085/testing05/LoginReturn";
        Mjson = new MakeJson();
        Id=id.getText().toString();
        Pw=pw.getText().toString();


        //이부분을 215번쨰줄 if 문에서 로그인 정보가 맞아서 화면을 띄워 줄떄 저장하는거로 하는게 맞는거일듯..?
        SharedPreferences auto = getSharedPreferences("auto", MODE_PRIVATE);
        SharedPreferences.Editor editor = auto.edit();
        editor.putString("ID", Id);
        editor.putString("PW", Pw);

        editor.commit();


        String result=null;

        JSONParser jsonParser = new JSONParser();
        LoginData data= new LoginData();

        json = Mjson.LoginJson(Id,Pw);
        NetworkTask networkTask = new NetworkTask(url, null,json);
        try
        {
            result = networkTask.execute().get();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        data = jsonParser.LoginJSONParse(result);

        //TODO: 로그인 정보 틀릴시 -> ppt 6쪽 팝업띄우기(제가 띄울게여 걍 이프문 하나만 열어주세여)
        if(data.Getlogin_fail()==0){
            //팝업
            Intent intent = new Intent(this, LoginFailPopup.class);
            startActivity(intent);
            //틀릴시

        }

        //TODO: 로그인 정보 맞을시 -> ppt 7쪽 페이지 인텐트 시키기(이거도 걍 이픔문 열어주시면 걍제가 페이지 만들어서 인텐트 시킬게영)
        else {
            if(data.GetORIGIN_ACCOUNT_DIVISION_ID() == 1 || data.GetORIGIN_ACCOUNT_DIVISION_ID() == 3)
            {
                if(isFirst == true)
                {
                    MemberName = data.GetMember_name();
                    Intent intent = new Intent(this, SettingActivity.class);
                    startActivity(intent);

                }
                else
                {
                    MemberName = data.GetMember_name();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }

            else
            {
                Intent intent = new Intent(this, LoginFailPopup.class);
                startActivity(intent);
            }

        }

    }

    //비밀번호 찾기 클릭
    public void findPWBtnOnClick(View view) {
        Intent intent = new Intent( getApplicationContext(),FindpwActivity.class);

        startActivity( intent );
    }


}

