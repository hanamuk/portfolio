package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



//새로 생성해야대는거
//ListViewAdapter.java -> 어뎁터
//ListViewItem.java -> 스트럭쳐 클래스
//

public class MainActivity extends AppCompatActivity {

    private static final int ITEM_VIEW_TYPE_MAIN = 0 ;
    public static Activity MainActivity_;
    public static Context main_context;


    private DrawerLayout drawerLayout;
    private View drawerView;
    private ImageView menu;
    private String test;
    private ArrayList<AllProcessData> mianreturndata;
    private TextView importcount;
    private TextView memName;
    public static int processid;
    private int qr_joborderid;

    public static ArrayList<AllProcessData> data;
    private ListViewAdapter adapter;
    private ArrayList<ListViewItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);



        MainActivity_ = MainActivity.this;
        if(WorkProcessActivity.WorkProcessActivity_!= null)
        {
            WorkProcessActivity.WorkProcessActivity_.finish();
        }
        SharedPreferences setting = getSharedPreferences("setting", MODE_PRIVATE);
        processid = setting.getInt("PROCESSID",0);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout1);
        drawerView = (View) findViewById(R.id.drawer);


        main_context = this;
        qr_joborderid = RecogQRActive.qr_joborderid;

        data= new ArrayList<>();
        data = GetMainData();

        menu = (ImageView) findViewById(R.id.menubtn);
        importcount = (TextView)findViewById(R.id.importnum);
        memName = (TextView)findViewById(R.id.membername);

        memName.setText(LoginActivity.MemberName);

        importcount.setText(Filter.main_warehousing_count + "건");
        Filter.main_warehousing_count = 0;



        //아이템리스트 하나랑, 그 아이템리스트를 담을 어레이리스트 하나랑, 어뎁터  <- 이렇게 세개 만들고 객체 생성해야함

        //data.size()
        for(int i = 0; i < 1; i++)
        {
            //ListViewItem이 내가 만든 그 스트럭쳐 클래스임 하나 생성하고
            ListViewItem item = new ListViewItem();
            
            //아이템에다가 data에서 넘어온거 포문돌면서 데이터 하나씩 넣어줌 
            /*AllProcessData pd = data.get(i);
            item.setType(ITEM_VIEW_TYPE_MAIN);
            item.setNum(i + 1);
            item.setJobname(pd.GetJOB_NAME());
            item.setStatus(pd.GetPROGRESS_STATUS());
            item.setBefore(pd.GetSTATE_BEFORE());
            item.setPresent(pd.GetSTATE_PRESENT());
            item.setNext(pd.GetSTATE_NEXT());
            item.setWarehouse_type(pd.GetWAREHOUSING());
            item.setJoborderid(pd.GetJOB_ID());
            item.setJoborder_process_id(pd.GetJOB_PROCESS_ID());
            item.setBgcolor(pd.GetCOLOR());*/

            AllProcessData pd = data.get(i);
            item.setType(ITEM_VIEW_TYPE_MAIN);
            item.setNum(i + 1);
            item.setJobname("-");
            item.setStatus("-");
            item.setBefore("-");
            item.setPresent("-");
            item.setNext("-");
            item.setWarehouse_type(1);
            item.setJoborderid(108);
            item.setJoborder_process_id(1);
            item.setBgcolor("1");
            
            //다 넣고나서 그 아이템을 어레이리스트에 넣어줌
            list.add(item);
            // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        }
        
        //걍 갖다쓰셈 이거 다 필요함
        RecyclerDecoration spaceDecoration = new RecyclerDecoration(0);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.main_listview) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
        recyclerView.addItemDecoration(spaceDecoration);

        // 여기가 실제 생성되는 부분 암튼 피료함
        adapter = new ListViewAdapter(list) ;
        recyclerView.setAdapter(adapter) ;
        recyclerView.addItemDecoration(spaceDecoration);

        drawerLayout.setScrimColor(Color.TRANSPARENT);

        // 메뉴 버튼 여는 리스너
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

    }



    public ArrayList<AllProcessData> GetMainData() {

        //수정해야됨
        String url = "http://220.89.167.212:8085/testing05/WorkerAllJbList";

        String result=null;

        JSONParser jsonParser = new JSONParser();
        mianreturndata= new ArrayList<>();


        GetNetworkTask getnetworkTask = new GetNetworkTask(url, null);
        try
        {
            result = getnetworkTask.execute().get();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        mianreturndata = jsonParser.MainJSONParse(result);

        //------
       /* test = test();
        mianreturndata = jsonParser.MainJSONParse(test);*/
        return mianreturndata;


    }


    public void qrBtnOnClick(View view) {
        Intent intent = new Intent( getApplicationContext(),RecogQRActive.class);
        startActivity( intent );
    }

    public void settingBtnOnclick(View view){
        Intent intent = new Intent( getApplicationContext(),SettingActivity.class);
        startActivity( intent );
    }

    public void logoutBtnOnClick(View view){
        SharedPreferences auto = getSharedPreferences("auto", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = auto.edit();
        editor1.putString("ID", "");
        editor1.putString("PW", "");
        editor1.commit();

        Intent intent = new Intent( getApplicationContext(), LoginActivity.class);
        startActivity( intent );
    }

    public void workProcessBtnOnClick(View view){
        Intent intent = new Intent( getApplicationContext(), WorkProcessActivity.class);
        startActivity( intent );
    }

    public void backBtnOnClick(View view){
        finish();
    }


    public String test() {

        //json 파일 읽어와서 분석하기

        //assets폴더의 파일을 가져오기 위해
        //창고관리자(AssetManager) 얻어오기
        String jsonData= "NULL";
        AssetManager assetManager= getAssets();

        //assets/ test.json 파일 읽기 위한 InputStream
        try {
            InputStream is= assetManager.open("jsons/jsontest.json");
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader reader= new BufferedReader(isr);

            StringBuffer buffer= new StringBuffer();
            String line= reader.readLine();
            while (line!=null){
                buffer.append(line+"\n");
                line=reader.readLine();
            }

            jsonData= buffer.toString();


        }
        catch (Exception e)
        {


        }
        return jsonData;
    }


}