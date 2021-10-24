package com.example.testapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {
    private LoginData Logindata;
    private PasswordFindData findData;
    // private AllProcessData mainData;
    private JSONArray jsonArray;
    private ArrayList<ProcessJSONData> JSONDataList;
    private ArrayList<ArrayList<ProcessJSONData>> AllJSONData = new ArrayList<ArrayList<ProcessJSONData>>();
    private ArrayList<AllProcessData> AllProcessDataList;
    private ArrayList<BtoBData> BtoBDataList;
    private ArrayList<IndividualData> IndividualDataList;
    private ArrayList<MainProcessCategory> Categories;



    public LoginData LoginJSONParse(String jsonStr)
    {
        StringBuilder stringBuilder = new StringBuilder();
        Logindata = new LoginData();

        try
        {
            /*JSONArray jsonArray = new JSONArray(jsonStr);
            for(int i = 0; i < jsonArray.length(); i++)
            {*/
            JSONObject jsonObject = new JSONObject(jsonStr);
            Logindata.Setlogin_fail(jsonObject.getInt("login_fail"));
            Logindata.SetPwd_find_fail(jsonObject.getInt("pwd_find_fail"));
            Logindata.SetId_Credit(jsonObject.getInt("Id_Credit"));
            Logindata.SetOverlap(jsonObject.getInt("overlap"));

            Logindata.SetWORKER_PROPERTY_ID(jsonObject.getInt("WORKER_PROPERTY_ID"));
            Logindata.SetORIGIN_EQUIPMENT_ID(jsonObject.getInt("ORIGIN_EQUIPMENT_ID"));
            Logindata.SetORIGIN_PROCESS_ID(jsonObject.getInt("ORIGIN_PROCESS_ID"));

            Logindata.SetORIGIN_ACCOUNT_ID(jsonObject.getInt("ORIGIN_ACCOUNT_ID"));
            Logindata.SetORIGIN_ACCOUNT_DIVISION_ID(jsonObject.getInt("ORIGIN_ACCOUNT_DIVISION_ID"));
            Logindata.SetORIGIN_ACCOUNT_MEMBERNAME(jsonObject.getString("ORIGIN_ACCOUNT_MEMBERNAME"));
            Logindata.SetORIGIN_ACCOUNT_PASSWORD(jsonObject.getString("ORIGIN_ACCOUNT_PASSWORD"));
            Logindata.SetORIGIN_ACCOUNT_TYPE(jsonObject.getString("ORIGIN_ACCOUNT_TYPE"));
            Logindata.SetORIGIN_ACCOUNT_EMPLOYEENUMBER(jsonObject.getString("ORIGIN_ACCOUNT_EMPLOYEENUMBER"));
            Logindata.SetORIGIN_ACCOUNT_DEPARTMENT(jsonObject.getString("ORIGIN_ACCOUNT_DEPARTMENT"));
            Logindata.SetORIGIN_ACCOUNT_TIER(jsonObject.getString("ORIGIN_ACCOUNT_TIER"));
            Logindata.SetORIGIN_ACCOUNT_RESIDENTNUMBER(jsonObject.getString("ORIGIN_ACCOUNT_RESIDENTNUMBER"));
            Logindata.SetORIGIN_ACCOUNT_CREATEDATE(jsonObject.getString("ORIGIN_ACCOUNT_CREATEDATE"));
            Logindata.SetORIGIN_ACCOUNT_EMPLOYMENT_STATUS_ID(jsonObject.getInt("ORIGIN_ACCOUNT_EMPLOYMENT_STATUS_ID"));

            // int age = jsonObject.getInt("age");
            //stringBuilder.append("이름 : ").append(name).append(" 나이 : ").append(age).append(" 주소 : ").append(address).append("\n");
            //}
            //mTextView.setText(stringBuilder);
            if(Logindata.GetORIGIN_ACCOUNT_DIVISION_ID() == 1 || Logindata.GetORIGIN_ACCOUNT_DIVISION_ID() == 3)
            {
                return Logindata;
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        //Logindata = new LoginData();
        return Logindata;

    }

    public ArrayList<MainProcessCategory> ProcessCategoryJSONParser(String jsonStr)
    {
        Categories = new ArrayList<>();

        try
        {
            jsonArray = new JSONArray(jsonStr);
            for(int i = 0 ; i<jsonArray.length(); i++){

                MainProcessCategory mainData = new MainProcessCategory();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                mainData.SetPROCESS_MAIN_CATEGORY_ID(jsonObject.getInt("PROCESS_MAIN_CATEGORY_ID"));
                mainData.SetPROCESS_MAIN_CATEGORY_NAME(jsonObject.getString("PROCESS_MAIN_CATEGORY_NAME"));

                Categories.add(mainData);
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        return Categories;

    }


    public PasswordFindData FindPaswordJSONParse(String jsonStr)
    {
        StringBuilder stringBuilder = new StringBuilder();
        findData = new PasswordFindData();

        try
        {
            /*JSONArray jsonArray = new JSONArray(jsonStr);
            for(int i = 0; i < jsonArray.length(); i++)
            {*/
            JSONObject jsonObject = new JSONObject(jsonStr);
            findData.Setlogin_fail(jsonObject.getInt("login_fail"));
            findData.SetPwd_find_fail(jsonObject.getInt("pwd_find_fail"));
            findData.SetId_Credit(jsonObject.getInt("Id_Credit"));
            findData.SetOverlap(jsonObject.getInt("overlap"));
            findData.SetORIGIN_ACCOUNT_ID(jsonObject.getInt("ORIGIN_ACCOUNT_ID"));
            findData.SetORIGIN_ACCOUNT_DIVISION_ID(jsonObject.getInt("ORIGIN_ACCOUNT_DIVISION_ID"));
            findData.SetWORKER_PROPERTY_ID(jsonObject.getInt("WORKER_PROPERTY_ID"));

            findData.SetORIGIN_EQUIPMENT_ID(jsonObject.getInt("ORIGIN_EQUIPMENT_ID"));
            findData.SetORIGIN_PROCESS_ID(jsonObject.getInt("ORIGIN_PROCESS_ID"));
            findData.SetORIGIN_ACCOUNT_PASSWORD(jsonObject.getString("ORIGIN_ACCOUNT_PASSWORD"));
            findData.SetORIGIN_ACCOUNT_EMPLOYMENT_STATUS_ID(jsonObject.getInt("ORIGIN_ACCOUNT_EMPLOYMENT_STATUS_ID"));



        } catch (JSONException e)
        {
            e.printStackTrace();
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        return findData;

    }


    public  ArrayList<AllProcessData> MainJSONParse(String jsonStr)
    {
        // StringBuilder stringBuilder = new StringBuilder();
        // mainData = new AllProcessData();
        AllProcessDataList = new ArrayList<>();
        JSONDataList = new ArrayList<>();

        try
        {
            int joborder_id = 0;
            jsonArray = new JSONArray(jsonStr);
            for(int i = 0 ; i< jsonArray.length(); i++){

                ProcessJSONData jsonData = new ProcessJSONData();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if(joborder_id == 0)
                {
                    joborder_id = jsonObject.getInt("JOBORDER_ID");
                }

                jsonData.SetJOBORDER_PROCESS_ID(jsonObject.getInt("JOBORDER_PROCESS_ID"));
                jsonData.SetPROCESS_MAIN_CATEGORY_ID(jsonObject.getInt("PROCESS_MAIN_CATEGORY_ID"));
                jsonData.SetJOBORDER_PROCESS_NAME(jsonObject.getString("JOBORDER_PROCESS_NAME"));
                jsonData.SetJOBORDER_PROCESS_INDEX(jsonObject.getInt("JOBORDER_PROCESS_INDEX"));
                jsonData.SetJOBORDER_PROCESS_FOCUSON(jsonObject.getInt("JOBORDER_PROCESS_FOCUSON"));
                jsonData.SetJOBORDER_PROCESS_WORKTIME(jsonObject.getInt("JOBORDER_PROCESS_WORKTIME"));
                jsonData.SetJOBORDER_ID(jsonObject.getInt("JOBORDER_ID"));
                jsonData.SetJOBORDER_LOBBY_ACTIVITY_ID1(jsonObject.getInt("JOBORDER_LOBBY_ACTIVITY_ID1"));
                jsonData.SetJOBORDER_LOBBY_ACTIVITY_ID2(jsonObject.getInt("JOBORDER_LOBBY_ACTIVITY_ID2"));
                jsonData.SetJOBORDER_LOBBY_ACTIVITY_ID3(jsonObject.getInt("JOBORDER_LOBBY_ACTIVITY_ID3"));
                jsonData.SetJOBORDER_EMERGENCY_ID(jsonObject.getInt("JOBORDER_EMERGENCY_ID"));
                jsonData.SetORIGIN_ACCOUNT_ID(jsonObject.getInt("ORIGIN_ACCOUNT_ID"));
                jsonData.SetJOBORDER_DETAIL_ID(jsonObject.getInt("JOBORDER_DETAIL_ID"));
                jsonData.SetJOBORDER_DETAIL_JOBNUMBER(jsonObject.getString("JOBORDER_DETAIL_JOBNUMBER"));



                if(joborder_id != jsonObject.getInt("JOBORDER_ID"))
                {
                    AllJSONData.add(JSONDataList);
                    JSONDataList = new ArrayList<>();
                    JSONDataList.add(jsonData);
                    joborder_id = jsonObject.getInt("JOBORDER_ID");
                }
                else
                {
                    JSONDataList.add(jsonData);
                }

            }
            //
            AllJSONData.add(JSONDataList);
            AllProcessDataList =  Filter.MainFilter(AllJSONData);


            //
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        return AllProcessDataList;

    }

    public  ArrayList<BtoBData>  BtoBJSONParse(String jsonStr)
    {
        BtoBDataList = new ArrayList<>();
        JSONDataList = new ArrayList<>();

        try
        {
            int joborder_id = 0;
            jsonArray = new JSONArray(jsonStr);
            for(int i = 0 ; i< jsonArray.length(); i++){

                ProcessJSONData jsonData = new ProcessJSONData();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if(joborder_id == 0)
                {
                    joborder_id = jsonObject.getInt("JOBORDER_ID");
                }

                jsonData.SetJOBORDER_PROCESS_ID(jsonObject.getInt("JOBORDER_PROCESS_ID"));
                jsonData.SetPROCESS_MAIN_CATEGORY_ID(jsonObject.getInt("PROCESS_MAIN_CATEGORY_ID"));
                jsonData.SetJOBORDER_PROCESS_NAME(jsonObject.getString("JOBORDER_PROCESS_NAME"));
                jsonData.SetJOBORDER_PROCESS_INDEX(jsonObject.getInt("JOBORDER_PROCESS_INDEX"));
                jsonData.SetJOBORDER_PROCESS_FOCUSON(jsonObject.getInt("JOBORDER_PROCESS_FOCUSON"));
                jsonData.SetJOBORDER_PROCESS_WORKTIME(jsonObject.getInt("JOBORDER_PROCESS_WORKTIME"));
                jsonData.SetJOBORDER_ID(jsonObject.getInt("JOBORDER_ID"));
                jsonData.SetJOBORDER_LOBBY_ACTIVITY_ID1(jsonObject.getInt("JOBORDER_LOBBY_ACTIVITY_ID1"));
                jsonData.SetJOBORDER_LOBBY_ACTIVITY_ID2(jsonObject.getInt("JOBORDER_LOBBY_ACTIVITY_ID2"));
                jsonData.SetJOBORDER_LOBBY_ACTIVITY_ID3(jsonObject.getInt("JOBORDER_LOBBY_ACTIVITY_ID3"));
                jsonData.SetJOBORDER_EMERGENCY_ID(jsonObject.getInt("JOBORDER_EMERGENCY_ID"));
                jsonData.SetORIGIN_ACCOUNT_ID(jsonObject.getInt("ORIGIN_ACCOUNT_ID"));
                jsonData.SetJOBORDER_DETAIL_ID(jsonObject.getInt("JOBORDER_DETAIL_ID"));
                jsonData.SetJOBORDER_DETAIL_JOBNUMBER(jsonObject.getString("JOBORDER_DETAIL_JOBNUMBER"));

                if(jsonObject.isNull("ORIGIN_ACCOUNT_MEMBERNAME"))
                {
                    jsonData.SetORIGIN_ACCOUNT_MEMBERNAME("-");
                }
                else
                {
                    jsonData.SetORIGIN_ACCOUNT_MEMBERNAME(jsonObject.getString("ORIGIN_ACCOUNT_MEMBERNAME"));
                }


                if(joborder_id != jsonObject.getInt("JOBORDER_ID"))
                {
                    AllJSONData.add(JSONDataList);
                    JSONDataList = new ArrayList<>();
                    JSONDataList.add(jsonData);
                    joborder_id = jsonObject.getInt("JOBORDER_ID");
                }
                else
                {
                    JSONDataList.add(jsonData);
                }

            }
            //
            AllJSONData.add(JSONDataList);
            BtoBDataList =  Filter.WorkProcessFilter(AllJSONData);


            //
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        return BtoBDataList;

    }

    public  ArrayList<BtoBData>  IndividualJSONParse(String jsonStr)
    {
        BtoBDataList = new ArrayList<>();
        JSONDataList = new ArrayList<>();

        try
        {
            int joborder_id = 0;
            jsonArray = new JSONArray(jsonStr);
            for(int i = 0 ; i< jsonArray.length(); i++){

                ProcessJSONData jsonData = new ProcessJSONData();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if(joborder_id == 0)
                {
                    joborder_id = jsonObject.getInt("JOBORDER_ID");
                }

                jsonData.SetJOBORDER_PROCESS_ID(jsonObject.getInt("JOBORDER_PROCESS_ID"));
                jsonData.SetPROCESS_MAIN_CATEGORY_ID(jsonObject.getInt("PROCESS_MAIN_CATEGORY_ID"));
                jsonData.SetJOBORDER_PROCESS_NAME(jsonObject.getString("JOBORDER_PROCESS_NAME"));
                jsonData.SetJOBORDER_PROCESS_INDEX(jsonObject.getInt("JOBORDER_PROCESS_INDEX"));
                jsonData.SetJOBORDER_PROCESS_FOCUSON(jsonObject.getInt("JOBORDER_PROCESS_FOCUSON"));
                jsonData.SetJOBORDER_PROCESS_WORKTIME(jsonObject.getInt("JOBORDER_PROCESS_WORKTIME"));
                jsonData.SetJOBORDER_ID(jsonObject.getInt("JOBORDER_ID"));
                jsonData.SetJOBORDER_LOBBY_ACTIVITY_ID1(jsonObject.getInt("JOBORDER_LOBBY_ACTIVITY_ID1"));
                jsonData.SetJOBORDER_LOBBY_ACTIVITY_ID2(jsonObject.getInt("JOBORDER_LOBBY_ACTIVITY_ID2"));
                jsonData.SetJOBORDER_LOBBY_ACTIVITY_ID3(jsonObject.getInt("JOBORDER_LOBBY_ACTIVITY_ID3"));
                jsonData.SetJOBORDER_EMERGENCY_ID(jsonObject.getInt("JOBORDER_EMERGENCY_ID"));
                jsonData.SetORIGIN_ACCOUNT_ID(jsonObject.getInt("ORIGIN_ACCOUNT_ID"));
                jsonData.SetJOBORDER_DETAIL_ID(jsonObject.getInt("JOBORDER_DETAIL_ID"));
                jsonData.SetJOBORDER_DETAIL_JOBNUMBER(jsonObject.getString("JOBORDER_DETAIL_JOBNUMBER"));

                if(jsonObject.isNull("ORIGIN_ACCOUNT_MEMBERNAME"))
                {
                    jsonData.SetORIGIN_ACCOUNT_MEMBERNAME("-");
                }
                else
                {
                    jsonData.SetORIGIN_ACCOUNT_MEMBERNAME(jsonObject.getString("ORIGIN_ACCOUNT_MEMBERNAME"));
                }


                if(joborder_id != jsonObject.getInt("JOBORDER_ID"))
                {
                    AllJSONData.add(JSONDataList);
                    JSONDataList = new ArrayList<>();
                    JSONDataList.add(jsonData);
                    joborder_id = jsonObject.getInt("JOBORDER_ID");
                }
                else
                {
                    JSONDataList.add(jsonData);
                }

            }
            //
            AllJSONData.add(JSONDataList);
            BtoBDataList =  Filter.IndividualFilter(AllJSONData);
            //
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        return BtoBDataList;

    }



    public int UpdateJSONParse(String jsonStr, int a)
    {
        StringBuilder stringBuilder = new StringBuilder();
        int result = 0;
        try
        {
            JSONObject jsonObject = new JSONObject(jsonStr);

            switch (a)
            {
                //입고
                case 1:
                    result = jsonObject.getInt("UpdateReceiveOn1");
                    break;
                 //할당   
                case 2:
                    result = jsonObject.getInt("UpdateAssignOn1");
                    break;
                 //입고취소   
                case 3:
                    result = jsonObject.getInt("UpdateReceiveCancel");
                    break;
                 //할당취소
                case 4:
                    result = jsonObject.getInt("UpdateAssignCancel");
                    break;
            }



        } catch (JSONException e)
        {
            e.printStackTrace();
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        return result;

    }


}