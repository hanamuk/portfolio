package com.example.testapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingPopup extends Activity {

    private String title;
    private TextView spinner_title;
    private String selectedTxt;
    private int num;
    private ArrayAdapter adapter;
    private ListView listView;
    public int processidd;
    public ArrayList<MainProcessCategory> processCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_setting);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        listView = (ListView) findViewById(R.id.spinner_listview);

        //String[] proarr = getResources().getStringArray( R.array.process_array );
        String[] equarr =getResources().getStringArray( R.array.equip_array );

        processCategories = GetProcessList();

        List prolist = new ArrayList();
        for(int i = 0; i < processCategories.size(); i ++)
        {
            MainProcessCategory data = processCategories.get(i);

            prolist.add(data.GetPROCESS_MAIN_CATEGORY_NAME());
        }

        //List prolist = new ArrayList(Arrays.asList(proarr));
        List equlist = new ArrayList(Arrays.asList(equarr));

        Intent intent = getIntent();
        title = intent.getStringExtra("data");
        num = intent.getIntExtra("num", 0);

        spinner_title = (TextView)findViewById(R.id.spinner_title);
        spinner_title.setText(title);


        if(num == 1){
            adapter = new ArrayAdapter(this, R.layout.item_listview, prolist);
            listView.setAdapter(adapter);
        }

        if(num == 2) {
            adapter = new ArrayAdapter(this, R.layout.item_listview, equlist);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTxt = (String) adapter.getItem(position);
                if(num == 1)
                {
                    for(int i = 0; i < processCategories.size(); i ++)
                    {
                        MainProcessCategory data = processCategories.get(i);
                        if((String) adapter.getItem(position) == data.GetPROCESS_MAIN_CATEGORY_NAME())
                        {
                            processidd = data.GetPROCESS_MAIN_CATEGORY_ID();
                        }
                    }
                }

            }
        });

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

    public void backBtnPressed(View view)  {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("num", num);
        resultIntent.putExtra("result", selectedTxt);
        resultIntent.putExtra("processid", processidd);
        setResult(RESULT_OK,resultIntent);
        finish();
    }

    public ArrayList<MainProcessCategory> GetProcessList() {

        //수정해야됨
        String url = "http://220.89.167.212:8085/testing05/MainProcessList";

        String result=null;

        JSONParser jsonParser = new JSONParser();
        processCategories= new ArrayList<>();


        GetNetworkTask getnetworkTask = new GetNetworkTask(url, null);
        try
        {
            result = getnetworkTask.execute().get();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        processCategories = jsonParser.ProcessCategoryJSONParser(result);

        //------
        return processCategories;

    }

}