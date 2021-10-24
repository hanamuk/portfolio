package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WorkProcessActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View drawerView;
    private ImageView menu;
    private ImageView alloc;
    private ArrayList<AllProcessData> TempData;
    private ArrayList<BtoBData> btobdata;
    private ArrayList<BtoBData> individualData;
    private LayoutInflater mInflater;
    private TextView firstmenuname;
    private TextView secondmenuname;
    private TextView memName;
    private TextView importnum;
    private TextView assignnum;
    private TextView indi_wait;
    private TextView indi_proceed;
    private TextView indi_finish;
    private String test;
    private String test1;
    private String a, b;
    private String Process;

    private WorkLIstViewAdapter adapter;
    private IndividualListViewAdapter adapter1;
    private ArrayList<WorkListViewItem> list = new ArrayList<>();
    private ArrayList<WorkListViewItem> list1 = new ArrayList<>();

    private static final int ITEM_VIEW_TYPE_WORK = 1 ;
    private static final int ITEM_VIEW_TYPE_INDI = 2 ;

    public static Activity WorkProcessActivity_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_workprocess);

        WorkProcessActivity_ = WorkProcessActivity.this;
        if(MainActivity.MainActivity_!= null)
        {
            MainActivity.MainActivity_.finish();
        }


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerView = (View) findViewById(R.id.drawer);
        menu = (ImageView) findViewById(R.id.menubtn);
        mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        memName = (TextView)findViewById(R.id.membername);
        importnum = (TextView)findViewById(R.id.importnum2);
        assignnum = (TextView)findViewById(R.id.assignnum);
        indi_wait = (TextView)findViewById(R.id.importnum5);
        indi_proceed = (TextView)findViewById(R.id.importnum4);
        indi_finish = (TextView)findViewById(R.id.importnum1);

        firstmenuname = (TextView) findViewById(R.id.menuname);
        //secondmenuname = (TextView) findViewById(R.id.menuname);

        memName.setText(LoginActivity.MemberName);


        SharedPreferences setting = getSharedPreferences("setting", MODE_PRIVATE);
        Process = setting.getString("PROCESS","");
       /* SharedPreferences auto = getSharedPreferences("auto", MODE_PRIVATE);
        SharedPreferences.Editor editor = auto.edit();
        editor.putBoolean("AUTO", false);
        editor.commit();*/

        firstmenuname.setText("" + Process);

        btobdata = new ArrayList<>();
        individualData = new ArrayList<>();

        //작업공정추가
        btobdata =  GetBtoBData();
        individualData = GetIndividualData();

        importnum.setText(Filter.btb_all_count + "건");
        Filter.btb_all_count = 0;

        assignnum.setText(Filter.btb_haldang_count + "건");
        Filter.btb_haldang_count = 0;

        indi_wait.setText(Filter.indi_daegie_count + "건");
        Filter.indi_daegie_count = 0;
        indi_proceed.setText(Filter.indi_jinhanging_count + "건");
        Filter.indi_jinhanging_count = 0;
        indi_finish.setText(Filter.indi_wanryo_count + "건");
        Filter.indi_wanryo_count = 0;

        //아이템리스트 하나랑, 그 아이템리스트를 담을 어레이리스트 하나랑, 어뎁터  <- 이렇게 세개 만들고 객체 생성해야함
        for(int i = 0; i < btobdata.size(); i++)
        {
            //ListViewItem이 내가 만든 그 스트럭쳐 클래스임 하나 생성하고
            WorkListViewItem item = new WorkListViewItem();

            //아이템에다가 data에서 넘어온거 포문돌면서 데이터 하나씩 넣어줌
            BtoBData pd = btobdata.get(i);

            item.setType(ITEM_VIEW_TYPE_WORK);
            item.setNum(i + 1);
            item.setJobname(pd.GetJOB_NAME());
            item.setStatus(pd.GetPROGRESS_STATUS());
            item.setBefore(pd.GetSTATE_BEFORE());
            item.setPresent(pd.GetSTATE_PRESENT());
            item.setNext(pd.GetSTATE_NEXT());
            item.setWorker(pd.GetWORKER_NAME());
            item.setAssigment(pd.GetASSIGNMENT());
            item.setWarehousing(pd.GetWAREHOSEING());
            item.setAssigment(pd.GetASSIGNMENT());
            item.setBgcolor(pd.GetCOLOR());
            item.setJoborderid(pd.GetJOB_ID());
            item.setJoborder_process_id(pd.GetJOB_PROCESS_ID());
            item.setOrigin_account_id(pd.GetORIGIN_ACCOUNT_ID());


            //다 넣고나서 그 아이템을 어레이리스트에 넣어줌
            list.add(item);
            // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        }

        for(int i = 0; i < individualData.size(); i++)
        {
            //ListViewItem이 내가 만든 그 스트럭쳐 클래스임 하나 생성하고
            WorkListViewItem item = new WorkListViewItem();

            //아이템에다가 data에서 넘어온거 포문돌면서 데이터 하나씩 넣어줌
            BtoBData pd = individualData.get(i);

            item.setType(ITEM_VIEW_TYPE_WORK);
            item.setNum(i + 1);
            item.setJobname(pd.GetJOB_NAME());
            item.setStatus(pd.GetPROGRESS_STATUS());
            item.setBefore(pd.GetSTATE_BEFORE());
            item.setPresent(pd.GetSTATE_PRESENT());
            item.setNext(pd.GetSTATE_NEXT());
            item.setWorker(pd.GetWORKER_NAME());
            item.setAssigment(pd.GetASSIGNMENT());
            item.setWarehousing(pd.GetWAREHOSEING());
            item.setAssigment(pd.GetASSIGNMENT());
            item.setBgcolor(pd.GetCOLOR());
            item.setJoborderid(pd.GetJOB_ID());
            item.setJoborder_process_id(pd.GetJOB_PROCESS_ID());
            item.setOrigin_account_id(pd.GetORIGIN_ACCOUNT_ID());
            item.setFocus_on(pd.GetFOCUS_ON());


            //다 넣고나서 그 아이템을 어레이리스트에 넣어줌
            list1.add(item);
            // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        }

        //Back to Back
        RecyclerDecoration spaceDecoration = new RecyclerDecoration(0);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.btb_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
        recyclerView.addItemDecoration(spaceDecoration);

        adapter = new WorkLIstViewAdapter(list) ;
        recyclerView.setAdapter(adapter) ;
        recyclerView.addItemDecoration(spaceDecoration);

        //개인작업
        spaceDecoration = new RecyclerDecoration(0);
        recyclerView = (RecyclerView)findViewById(R.id.indi_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
        recyclerView.addItemDecoration(spaceDecoration);

        adapter1 = new IndividualListViewAdapter(list1) ;
        recyclerView.setAdapter(adapter1) ;
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




    //작업공정
    public ArrayList<BtoBData> GetBtoBData() {


        //수정해야됨
        String url = "http://220.89.167.212:8085/testing05/WorkerPrcoessList";

        String result=null;

        JSONParser jsonParser = new JSONParser();
        btobdata= new ArrayList<>();


        GetNetworkTask getnetworkTask = new GetNetworkTask(url, null);
        try
        {
            result = getnetworkTask.execute().get();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        btobdata = jsonParser.BtoBJSONParse(result);

        //------
        /*test = test();
        btobdata = jsonParser.BtoBJSONParse(test);*/
        return btobdata;

    }

    public ArrayList<BtoBData> GetIndividualData() {


        //수정해야됨
        String url = "http://220.89.167.212:8085/testing05/WorkerPrcoessList";

        String result=null;

        JSONParser jsonParser = new JSONParser();
        individualData= new ArrayList<>();


        GetNetworkTask getnetworkTask = new GetNetworkTask(url, null);
        try
        {
            result = getnetworkTask.execute().get();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        individualData = jsonParser.IndividualJSONParse(result);

        //------

        return individualData;

    }

    public String test() {

        //json 파일 읽어와서 분석하기

        //assets폴더의 파일을 가져오기 위해
        //창고관리자(AssetManager) 얻어오기
        String jsonData= "NULL";
        AssetManager assetManager= getAssets();

        //assets/ test.json 파일 읽기 위한 InputStream
        try {
            InputStream is= assetManager.open("jsons/jsontest_pro.json");
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

    public String test1() {

        //json 파일 읽어와서 분석하기

        //assets폴더의 파일을 가져오기 위해
        //창고관리자(AssetManager) 얻어오기
        String jsonData= "NULL";
        AssetManager assetManager= getAssets();

        //assets/ test.json 파일 읽기 위한 InputStream
        try {
            InputStream is= assetManager.open("jsons/jsontest_individual.json");
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

    public void mainProcessBtnOnClick(View view){
        Intent intent = new Intent( getApplicationContext(), MainActivity.class);
        startActivity( intent );
    }



    public void backBtnOnClick(View view){
        finish();
    }
}